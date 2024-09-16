package christmas.domain;

public class VisitDate {
    private final int date;

    public VisitDate(final int date){
        validateVisitDate(date);
        this.date = date;
    }

    private void validateVisitDate(final int date){
    }

    public int getDate(){
        return date;
    }
}
