package dev.naman.lldassignments.designpatterns.singleton.printspooler.problem;

import dev.naman.lldassignments.designpatterns.singleton.printspooler.solution.PrintSpooler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintSpoolerTest {

    @Test
    void testSingletonInstance() {
        PrintSpooler instance1 = PrintSpooler.getInstance();
        PrintSpooler instance2 = PrintSpooler.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    void testAddAndProcessPrintJobs() {
        PrintSpooler printSpooler = PrintSpooler.getInstance();
        printSpooler.addPrintJob("Job 1");
        printSpooler.addPrintJob("Job 2");

        assertEquals("Job 1", printSpooler.processNextJob());
        assertEquals("Job 2", printSpooler.processNextJob());
        assertNull(printSpooler.processNextJob());
    }
}
