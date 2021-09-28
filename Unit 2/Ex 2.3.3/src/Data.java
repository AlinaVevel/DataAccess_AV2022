public class Data {

    private int day;
    private int month;
    private int year;


    public Data(int day, int month, int year) throws Exception {
        if (checkData(day, month,year) == 0) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

    }


    public int getDay() {
        return day;
    }


    public int getMonth() {
        return month;
    }

    public int checkData(int day, int month, int year) throws Exception {

        if (month < 1 || month > 12) {
            throw new Exception();
        }
        if (day < 1 || day > 31) {
            throw new Exception();
        }

        if (isALeadYear(year) && day != 29) {
            throw new Exception();
        }

        if(!isALeadYear(year) && day != 28){
            throw new Exception();
        }

        return 0;


    }

    public int getYear() {
        return year;
    }


    public boolean isALeadYear(int year) {

        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            }
        }

        if (year % 100 == 1) {
            return true;
        } else return false;


    }

}
