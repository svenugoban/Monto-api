package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Classroom")
public class Classroom {


        @Id
        String id;
        public String studentname;
        String teachername;
        String classname;

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public String getTeachername() {
            return teachername;
        }

        public void setTeachername(String teachername) {
            this.teachername = teachername;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }


    public Classroom() {
    }

    public Classroom(String classname) {
        this.classname = classname;
    }

    public Classroom(String classname,String studentname)
    {
        this.studentname = studentname;
        this.classname = classname;
    }
}


