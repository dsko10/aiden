package com.example.aiden.service;

import com.example.aiden.model.WorkTime;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

import static java.util.Arrays.stream;

@Service
public class WorkTimeProvider {

    public Map<WorkTime, String> getWorkTimeEntriesAsMap() {
        Map<WorkTime, String> workTimeEntries = new TreeMap<>();
        stream(WorkTime.values()).forEach(workTime -> workTimeEntries.put(workTime, workTime.getName()));
        return workTimeEntries;
    }
}
