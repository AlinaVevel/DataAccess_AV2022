import java.util.ArrayList;
public class Ex_2{
    public static void main(String[] args) {
        class Student {
            private String name;

            private int note;

            public String GetName() {
                return name;
            }


            public void SetName(String nombre) {
                this.name = nombre;
            }

            public int getNote() {

                return note;
            }

            public void setNote(int nota) {
                this.note = nota;
            }

            public Boolean Aprobado() {
                {
                    if (note >= 5)
                        return true;
                    else
                        return false;
                }
            }
        }

        class Students {
            private ArrayList<Student> listOfStudents = new ArrayList<Student>();

            // insert new student on the list
            //
            public void Instert(Student alu) {
                listOfStudents.add(alu);
            }

            // return the student that is on position num
            //
            public Student GetNumber(int num) {
                if (num >= 0 && num <= listOfStudents.size()) {
                    return listOfStudents.get(num);
                }
                return null;
            }

            // return the note of student

            public float Average() {
                {
                    if (listOfStudents.size() == 0)
                        return 0;
                    else {
                        float average = 0;
                        for (int i = 0; i < listOfStudents.size(); i++) {
                            average += listOfStudents.get(i).getNote();
                        }
                        return (average / listOfStudents.size());
                    }
                }
            }

            class StudentFCT extends Student {


                protected String company;
                protected String tutor;
                protected String instructor;

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getTutor() {
                    return tutor;
                }

                public void setTutor(String tutor) {
                    this.tutor = tutor;
                }

                public String getInstructor() {
                    return instructor;
                }

                public void setInstructor(String instructor) {
                    this.instructor = instructor;
                }


            }

            class AlunoErasmus extends Student {
                protected String start;
                protected String end;
                protected String originCountry;

                public String getStart() {
                    return start;
                }

                public void setStart(String start) {
                    this.start = start;
                }

                public String getEnd() {
                    return end;
                }

                public void setEnd(String end) {
                    this.end = end;
                }

                public String getOriginCountry() {
                    return originCountry;
                }

                public void setOriginCountry(String originCountry) {
                    this.originCountry = originCountry;
                }


            }
        }




    }
}
