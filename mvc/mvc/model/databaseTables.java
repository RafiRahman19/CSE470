package sample;

public class databaseTables {

    private String user, teacher, classTeacher, subjectTeacher, student, classOne, classTwo, classThree;
    private String userDetails, teacherDetails, classTeacherDetails, subjectTeacherDetails, studentDetails,
            classOneDetails, classTwoDetails, classThreeDetails;

    protected String[] tableNames(){
        setNames();
        String[] names={user, teacher, classTeacher, subjectTeacher, student, classOne, classTwo, classThree};
        return names;
    }

    protected String[] tableDetails(){
        String [] tableDetails={getUser(), getTeacher(), getClassTeacher(), getSubjectTeacher(), getStudent(),
                     getClassOne(), getClassTwo(), getClassThree()};
        return tableDetails;
    }

    private void setNames(){
        user="user";
        teacher="teacher";
        classTeacher="class_teacher";
        subjectTeacher="subject_teacher";
        student="student";
        classOne="class_one";
        classTwo="class_two";
        classThree="class_three";
    }

    private String getUser() {
        setUserDetails();
        return userDetails;
    }

    private void setUserDetails(){
        userDetails="userID  INT NOT NULL," +
                "   userName  VARCHAR(45) NOT NULL," +
                "   password  VARCHAR(45) NOT NULL," +
                "   userType  VARCHAR(45) NOT NULL," +
                "   teacher_teacherID  INT NULL," +
                "   teacher_classTeacher_classTeacherID  INT NULL," +
                "  PRIMARY KEY ( userID)," +
                "  UNIQUE INDEX  userID_UNIQUE  ( userID  ASC)," +
                "  UNIQUE INDEX  userName_UNIQUE  ( userName  ASC)";
    }

    private String getTeacher(){
        setTeacherDetails();
        return teacherDetails;
    }

    private void setTeacherDetails(){
        teacherDetails="teacherID  INT NOT NULL," +
                "   teacherName  VARCHAR(45) NOT NULL," +
                "   teacherDes  VARCHAR(45) NOT NULL," +
                "   teacherType  VARCHAR(45) NOT NULL," +
                "   teacherAddress  VARCHAR(45) NULL," +
                "   teacherPhoneNumber  VARCHAR(45) NULL," +
                "   classTeacher_classTeacherID  INT NULL," +
                "   subjectTeacher_subjectTeacherID  INT NULL," +
                "  PRIMARY KEY ( teacherID )," +
                "  UNIQUE INDEX  teacherID_UNIQUE  ( teacherID  ASC)";
    }

    private String getClassTeacher(){
        setClassTeacherDetails();
        return classTeacherDetails;
    }

    private void setClassTeacherDetails(){
        classTeacherDetails="classTeacherID  INT NOT NULL," +
                "   classTeacherName  VARCHAR(45) NOT NULL," +
                "   classTeacherClass  VARCHAR(45) NOT NULL," +
                "   classTeacherSection  VARCHAR(45) NOT NULL," +
                "   classTeacherSubject  VARCHAR(45) NULL," +
                "  PRIMARY KEY ( classTeacherID )," +
                "  UNIQUE INDEX  classTeacherID_UNIQUE  ( classTeacherID  ASC)";
    }

    private String getSubjectTeacher(){
        setSubjectTeacherDetails();
        return subjectTeacherDetails;
    }

    private void setSubjectTeacherDetails(){
        subjectTeacherDetails="subjectTeacherID  INT NOT NULL," +
                "   subjectTeacherName  VARCHAR(45) NOT NULL," +
                "   subjectTeacherSubject  VARCHAR(45) NOT NULL," +
                "   subjectTeacherDes  VARCHAR(45) NOT NULL," +
                "   subjectTeacherClass  VARCHAR(45) NOT NULL," +
                "   subjectTeacherSection  VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY ( subjectTeacherID )," +
                "  UNIQUE INDEX  subjectTeacherID_UNIQUE  ( subjectTeacherID  ASC)";
    }

    private String getStudent(){
        setStudentDetails();
        return studentDetails;
    }

    private void setStudentDetails(){
        studentDetails="studentID  INT NOT NULL," +
                "   studentName  VARCHAR(45) NOT NULL," +
                "   class  INT NOT NULL," +
                "   rollNumber  INT NULL," +
                "   section  VARCHAR(45) NULL," +
                "   parentsName  VARCHAR(45) NULL," +
                "   classOne_classOneID  INT NULL," +
                "   classTwo_classTwoID  INT NULL," +
                "   classThree_classThreeID  INT NULL," +
                "  PRIMARY KEY ( studentID )," +
                "  UNIQUE INDEX  studentID_UNIQUE  ( studentID  ASC)";
    }

    private String getClassOne(){
        setClassOneDetails();
        return classOneDetails;
    }

    private void setClassOneDetails(){
        classOneDetails="classOneID  INT NOT NULL," +
                "   studentName  VARCHAR(45) NOT NULL," +
                "   section  VARCHAR(45) NOT NULL," +
                "   rollNumber  INT NULL," +
                "   banglaHY  INT NULL," +
                "   englishHY  INT NULL," +
                "   mathHY  INT NULL," +
                "   artHY  INT NULL," +
                "   sportsHY  INT NULL," +
                "   totalHY  INT NULL," +
                "   banglaFinal  INT NULL," +
                "   englishFinal  INT NULL," +
                "   mathFinal  INT NULL," +
                "   artFinal  INT NULL," +
                "   sportsFinal  INT NULL," +
                "   totalFinal  INT NULL," +
                "   grandTotal  INT NULL," +
                "  PRIMARY KEY ( classOneID )," +
                "  UNIQUE INDEX  classOneID_UNIQUE  ( classOneID  ASC)";
    }

    private String getClassTwo(){
        setClassTwoDetails();
        return classTwoDetails;
    }

    private void setClassTwoDetails(){
        classTwoDetails="classTwoID  INT NOT NULL," +
                "   studentName  VARCHAR(45) NULL," +
                "   section  VARCHAR(45) NULL," +
                "   rollNumber  INT NULL," +
                "   banglaHY  INT NULL," +
                "   englishHY  INT NULL," +
                "   mathHY  INT NULL," +
                "   artHY  INT NULL," +
                "   sportsHY  INT NULL," +
                "   total  INT NULL," +
                "   banglaFinal  INT NULL," +
                "   englishFinal  INT NULL," +
                "   mathFinal  INT NULL," +
                "   artFinal  INT NULL," +
                "   sportsFinal  INT NULL," +
                "   totalFinal  INT NULL," +
                "   grandTotal  INT NULL," +
                "  PRIMARY KEY ( classTwoID )";
    }

    private String getClassThree(){
        setClassThreeDetails();
        return classThreeDetails;
    }

    private void setClassThreeDetails(){
        classThreeDetails="classThreeID  INT NOT NULL," +
                "   studentName  VARCHAR(45) NOT NULL," +
                "   section  VARCHAR(45) NULL," +
                "   rollNumber  INT NOT NULL," +
                "   banglaHY  INT NULL," +
                "   englishHY  INT NULL," +
                "   mathHY  INT NULL," +
                "   socialScienceHY  INT NULL," +
                "   scienceHY  INT NULL," +
                "   religionHY  INT NULL," +
                "   totalHY  INT NULL," +
                "   banglaFinal  INT NULL," +
                "   englishFinal  INT NULL," +
                "   mathFinal  INT NULL," +
                "   socialScienceFinal  INT NULL," +
                "   scienceFinal  INT NULL," +
                "   religionFinal  INT NULL," +
                "   totalFinal  INT NULL," +
                "   grandTotal  INT NULL," +
                "  PRIMARY KEY ( classThreeID )," +
                "  UNIQUE INDEX  classThreeID_UNIQUE  ( classThreeID  ASC)";
    }



}
