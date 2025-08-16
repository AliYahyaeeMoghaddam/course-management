package com.example.course_managment.features.gpa;

import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ParallelGpaCalculator {

    public void service(List<Student> students) throws InterruptedException {

        if (students == null || students.isEmpty()) return;

        File dir = new File("output");
        if (!dir.exists()) dir.mkdirs();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        final Student POISON_PILL = new Student("", "", null, "", null);

        BlockingQueue<Student> queue = new LinkedBlockingQueue<>();

        // --- Level 1: محاسبه معدل
        int aveThreads = 8;
        ExecutorService aveExe = Executors.newFixedThreadPool(aveThreads);

        for (List<Student> part : divideList(students, aveThreads)) {
            aveExe.submit(() -> {
                for (Student s : part) {
                    double sum = 0;
                    int totalCourses = 0;

                    if (s.getCourses() != null) {
                        for (var course : s.getCourses()) {
                            List<GradeCourse> gcs = course.getGradeCourse();
                            if (gcs != null && !gcs.isEmpty()) {
                                for (GradeCourse gc : gcs) {
                                    sum += gc.getGarde();
                                    totalCourses++;
                                }
                            }
                        }
                    }

                    double ave = totalCourses > 0 ? sum / totalCourses : 0;
                    if (s.getAverageStudent() != null) {
                        s.getAverageStudent().setAverage(ave);
                    }

                    try {
                        queue.put(s);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        aveExe.shutdown();
        aveExe.awaitTermination(5, TimeUnit.MINUTES);

        // Poison pills
        int fileThreads = 4;
        for (int i = 0; i < fileThreads; i++) {
            queue.put(POISON_PILL);
        }

        // --- Level 2: نوشتن فایل‌ها
        ExecutorService fileExe = Executors.newFixedThreadPool(fileThreads);

        for (int i = 0; i < fileThreads; i++) {
            fileExe.submit(() -> {
                try {
                    while (true) {
                        Student stud = queue.take();
                        if (stud == POISON_PILL) break;

                        File outFile = new File(dir, stud.getStudent_id() + ".json");
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
                            mapper.writeValue(writer, stud);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
        }

        fileExe.shutdown();
        if (!fileExe.awaitTermination(5, TimeUnit.MINUTES)) {
            fileExe.shutdownNow();
        }
    }

    private static List<List<Student>> divideList(List<Student> students, int numberOfThreads) {
        List<List<Student>> parts = new ArrayList<>();
        int size = students.size();
        int partSize = (int) Math.ceil((double) size / numberOfThreads);

        for (int i = 0; i < size; i += partSize) {
            int end = Math.min(i + partSize, size);
            parts.add(students.subList(i, end));
        }
        return parts;
    }
}
