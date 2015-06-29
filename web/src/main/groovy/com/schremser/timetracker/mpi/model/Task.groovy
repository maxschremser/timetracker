package com.schremser.timetracker.mpi.model

/**
 * Created by bluemax on 27.06.15.
 */
class Task {
    String details
    String time
    boolean running

    def getRunning() {
        return running
    }

    @Override
    String toString() {
        return "{details:$details, time:$time, running:$running},"
    }
}
