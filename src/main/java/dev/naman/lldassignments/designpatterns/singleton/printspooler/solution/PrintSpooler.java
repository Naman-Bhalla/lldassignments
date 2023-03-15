package dev.naman.lldassignments.designpatterns.singleton.printspooler.solution;

import java.util.LinkedList;
import java.util.Queue;

public class PrintSpooler {
    private static PrintSpooler instance;
    private final Queue<String> printQueue;

    private PrintSpooler() {
        printQueue = new LinkedList<>();
    }

    public static PrintSpooler getInstance() {
        if (instance == null) {
            instance = new PrintSpooler();
        }
        return instance;
    }

    public void addPrintJob(String job) {
        printQueue.add(job);
    }

    public String processNextJob() {
        if (printQueue.isEmpty()) {
            return null;
        }
        return printQueue.poll();
    }
}
