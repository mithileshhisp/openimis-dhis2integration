package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim;

import java.time.LocalDate;

public class BillablePeriod {
    private LocalDate start;
    private LocalDate end;

    public BillablePeriod() {

    }

    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "BillablePeriod [start=" + start + ", end=" + end+ "]";
    }
}
