package com.schremser.timetracker.mpi.model

import com.fasterxml.jackson.annotation.JsonIgnore

/*
 * A Track represents
 */

class Track {
    List<Task> tasks
    String name
    String date
    String project
    boolean billable

    def getBillable() {
        return billable
    }

    @Override
    String toString() {
        def _tl = ""
        tasks.each {_t -> _tl += _t.toString()}
        return "{name: $name, date: $date, project: $project, billable: $billable}"
    }
}

