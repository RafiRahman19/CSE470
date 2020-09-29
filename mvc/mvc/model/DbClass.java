package sample;

import java.sql.*;

public class DbClass {

    private static String databaseName="schoolautomationdatabase";
    private static String [] [] tableNameAndParameter=null;
    private static Connection conn = null;
    private static Statement statement;
    private static String sql;
    private static ResultSet resultSet, rs, rs1;


    public DbClass(String address, String user, String password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(address, user, password);

            System.out.println("conn built");
            databaseExist(databaseName);
            statement = conn.createStatement();
            sql = "use " + databaseName;
            statement.executeUpdate(sql);
            createTableNameAndParameter();
            for (int i = 0; i < tableNameAndParameter.length; i++) {
                if (!tableExist(databaseName, tableNameAndParameter[i][0]))
                    createTable(databaseName, tableNameAndParameter[i][0], tableNameAndParameter[i][1]);
            }
            if(!nameExist("ADMIN"))
                addUser("ADMIN", "admin", "system admin", 0,
                        0);

            if(!teacherExists("REZAUL KARIM")){
                addUser("REZAUL KARIM", "karim", "teacher", 1,
                        1);
                addTeacher("REZAUL KARIM", "senior", "class teacher", "", "",
                        1,1);
                addClassTeacher("REZAUL KARIM", "3", "A", "english");
                addSubjectTeacher("REZAUL KARIM", "english", "senior", "3",
                        "A");
                addSubjectTeacher("REZAUL KARIM", "english", "senior", "2",
                        "B");
                addStudent("Nafiur Rahman", 2, 1, "B", "Jaminur Rahman", 0, 1,
                        0);
                addClassTwo("Nafiur Rahman", "B", 1);
                addStudent("Hasibur Rahman", 3, 1, "A", "Jaminur Rahman", 0, 1,
                        0);
                addClassThree("Hasibur Rahman", "A", 1);
            }



        } catch (Exception e){
            e.printStackTrace();
        }

    }

    protected DbClass(){}

    private static void databaseExist(String databaseName)
    {
        String getDatabaseName;
        ResultSet resultSet=null;
        boolean requiredDatabase=false;
        try {
            resultSet = conn.getMetaData().getCatalogs();
            System.out.println(resultSet);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try{
            while(resultSet.next())
            {
                getDatabaseName=resultSet.getString(1);
                if(getDatabaseName.equals(databaseName))
                {
                    requiredDatabase=true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if(requiredDatabase==false){
            createDatabase(databaseName);
        }
    }

    private static void createDatabase(String database)
    {
        String sql="create database "+database+";";
        System.out.println(sql);
        Statement stmt;
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully");

        }
        catch(Exception e)
        {
            System.out.println("Database creation failed\n");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static void createTableNameAndParameter()
    {
        databaseTables dmt=new databaseTables();
        String [] tablesName=dmt.tableNames(), tableDetails=dmt.tableDetails();

        tableNameAndParameter=new String[tablesName.length][2];

        for(int i=0; i<tablesName.length; i++){
            tableNameAndParameter[i][0]=tablesName[i];
            tableNameAndParameter[i][1]=tableDetails[i];
        }
    }

    private static boolean tableExist(String databaseName, String tableName) throws Exception
    {
        boolean isTableExist=false;
        try{
            DatabaseMetaData metaData=conn.getMetaData();
            resultSet=metaData.getTables( databaseName, null,"%", null);
            String str;
            while(resultSet.next())
            {
                str=resultSet.getString("TABLE_NAME");
                if(str.equals(tableName)){
                    isTableExist=true;
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
//        System.out.println("Table Name : "+tableName+" Existence : "+isTableExist);
        return isTableExist;
    }

    private static boolean createTable(String databaseName, String tableName, String nameParameter)
    {
        boolean creationSuccessful=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="create table "+tableName+" ("+nameParameter+");";
            System.out.println(sql);
            statement.executeUpdate(sql);
            creationSuccessful=true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return creationSuccessful;
    }

    protected boolean nameExist(String userName){
        boolean boo=false;
        try{
            String tempUser;
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select userName from user order by userID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                tempUser=resultSet.getString("userName");
//                System.out.println(tempUser+" "+tempPassword);
                if(tempUser.equals(userName))
                    return true;
            }

        }catch(NullPointerException npe){
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return boo;
    }

    protected boolean addUser(String name, String password, String userType, int teacher_teacherID
            , int teacher_classTeacher_classTeacherID){
//        boolean boo=false;
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(userID) from user";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(userID)");

            sql="insert into user values ("+(max+1)+", '"+name+"', '"+password+"', '"+ userType+"', '"+teacher_teacherID+"', '"
                    +teacher_classTeacher_classTeacherID+"');";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected String getLogInInfo(String userName, String password){
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT USERNAME, PASSWORD, USERTYPE  FROM USER WHERE USERNAME ='"+userName+"';";
            resultSet=statement.executeQuery(sql);
            resultSet.next();
            String name=resultSet.getString("userName");
            String tempPass=resultSet.getString("password");
            String userType=resultSet.getString("userType");

            if(userName.equals(name) && password.equals(tempPass)){
                return userType;
            }else {
                return  "no match";
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private boolean teacherExists(String name){
        try{
            String tempName;
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select teacherName from teacher order by teacherID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                tempName=resultSet.getString("teacherName");
//                System.out.println(tempUser+" "+tempPassword);
                if(tempName.equals(name))
                    return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected boolean addTeacher(String name, String description, String type, String address, String phoneNumber,
                                 int classTeacherID, int subjectTeacherID){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(teacherID) from teacher";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(teacherID)");

            sql="INSERT INTO teacher Values ("+(max+1)+", '"+name+"', '"+description+"', '"+ type+"', '"+address+"', '"
                    +phoneNumber+"', "+classTeacherID+", "+subjectTeacherID+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected boolean addClassTeacher(String name, String teacherClass, String section, String subject){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(classTeacherID) FROM class_teacher";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(classTeacherID)");

            sql="INSERT INTO class_teacher Values ("+(max+1)+", '"+name+"', '"+teacherClass+"', '"+ section+"', '"+
                    subject+"');";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    protected boolean addSubjectTeacher(String name, String subject, String teacherDescription, String teacherClass,
                                        String section){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(subjectTeacherID) FROM subject_teacher";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(subjectTeacherID)");

            sql="INSERT INTO subject_teacher Values ("+(max+1)+", '"+name+"', '"+subject+"', '"+ teacherDescription+"', '"+
                    teacherClass+"', '"+section+"');";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected boolean addStudent(String name, int sClass, int roll, String section, String pName, int coID, int ctID,
                                 int cthID){
        try {
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(studentID) FROM student";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(studentID)");

            sql="INSERT INTO student Values ("+(max+1)+", '"+name+"', "+sClass+", "+ roll+", '"+section+"', '"+
                    pName+"', "+coID+", "+ctID+", "+cthID+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean addClassOne(String name, String section, int roll){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(classOneID) FROM class_one";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(classOneID)");

            sql="INSERT INTO class_one Values ("+(max+1)+", '"+name+"', '"+section+"', "+ roll+", "+0+", "+0+", "+
                    0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean addClassTwo(String name, String section, int roll){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(classTwoID) FROM class_two";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(classTwoID)");

            sql="INSERT INTO class_two Values ("+(max+1)+", '"+name+"', '"+section+"', "+ roll+", "+0+", "+0+", "+
                    0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean addClassThree(String name, String section, int roll){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(classThreeID) FROM class_three";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(classThreeID)");

            sql="INSERT INTO class_three Values ("+(max+1)+", '"+name+"', '"+section+"', "+ roll+", "+0+", "+0+", "+
                    0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+", "+0+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private String getClassTableName(int i){
        if(i==1)
            return "class_one";
        else if(i==2)
            return "class_two";
        else if(i==3)
            return "class_three";
        else
            return null;
    }

    protected boolean editPrimarySubjectNumber(String tName,int sClass, String section, int roll, String subject,
                                               String term, int score){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            boolean operationAllowed=false;
            sql="SELECT subjectTeacherSubject  FROM subject_teacher  WHERE subjectTeacherName='"+tName+
                    "' && subjectTeacherClass='"+sClass+"' && subjectTeacherSection='"+section+"';";
            rs = statement.executeQuery(sql);
            rs.next();
            String tempSubject=rs.getString("subjectTeacherSubject");
            if(tempSubject.equals(subject)){
                if(editPrimarySubjectNumber(sClass, section, roll, subject, term, score))
                    return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean editPrimarySubjectNumber(int sClass, String section, int roll, String subject, String term, int score){
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            String tableName=getClassTableName(sClass);
            String columnName=subject;
            if(term.equals("half yearly"))
                columnName=columnName+"HY";
            else if(term.equals("final"))
                columnName=columnName+"Final";
            else
                return false;

            int previousScore, difference, previousTerm, previousGrandFinal;
            sql="SELECT "+columnName+" FROM "+sClass+" WHERE section='"+section+"' && rollNumber="+roll+";";
            rs = statement.executeQuery(sql);
            rs.next();
            previousScore=rs.getInt(columnName);
            difference=score-previousScore;
            sql="UPDATE "+tableName+" SET "+columnName+"="+score+"  WHERE section='"+section+"' && rollNumber="+roll+";";
            statement.executeUpdate(sql);
            if(term.equals("half yearly")){
                sql="SELECT totalHY FROM "+sClass+" WHERE section='"+section+"' && rollNumber="+roll+";";
                rs = statement.executeQuery(sql);
                rs.next();
                previousTerm=rs.getInt("totalHY");
                sql="UPDATE "+tableName+" SET totalHY="+(previousTerm+difference)+"  WHERE section='"+section+
                        "' && rollNumber="+roll+";";
                statement.executeUpdate(sql);
            }else{
                sql="SELECT totalFinal FROM "+sClass+" WHERE section='"+section+"' && rollNumber="+roll+";";
                rs = statement.executeQuery(sql);
                rs.next();
                previousTerm=rs.getInt("totalFinal");
                sql="UPDATE "+tableName+" SET totalFinal="+(previousTerm+difference)+"  WHERE section='"+section+
                        "' && rollNumber="+roll+";";
                statement.executeUpdate(sql);
            }

            sql="SELECT grandTotal FROM "+sClass+" WHERE section='"+section+"' && rollNumber="+roll+";";
            rs = statement.executeQuery(sql);
            rs.next();
            previousGrandFinal=rs.getInt("grandTotal");
            sql="UPDATE "+tableName+" SET grandTotal="+(previousGrandFinal+difference)+"  WHERE section='"+section+
                    "' && rollNumber="+roll+";";
            statement.executeUpdate(sql);


            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected String[] getClassTeacherSection(String teachersName){
        try{
            String[] classTeacher=new String[3];
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT classTeacherClass, classTeacherSection, classTeacherSubject  FROM class_teacher" +
                    "  WHERE classTeacherName='"+teachersName+"';";
            rs=statement.executeQuery(sql);
            rs.next();
            classTeacher[0]=rs.getString("classTeacherClass");
            classTeacher[1]=rs.getString("classTeacherSection");
            classTeacher[2]=rs.getString("classTeacherSubject");
            return classTeacher;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    protected String[][] getSubjectTeacherSection(String teachersName){
        try {
            String [][] subjectTeacher;
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT subjectTeacherClass, subjectTeacherSection, subjectTeacherSubject  FROM subject_teacher WHERE" +
                    " subjectTeacherName='"+teachersName+"' ORDER BY subjectTeacherClass;";
            resultSet=statement.executeQuery(sql);

            resultSet.last();
            int size=resultSet.getRow();
            resultSet.beforeFirst();

            subjectTeacher=new String[size][3];
            int i=0;
            while(resultSet.next()){
                subjectTeacher[i][0]=resultSet.getString("subjectTeacherClass");
                subjectTeacher[i][1]=resultSet.getString("subjectTeacherSection");
                subjectTeacher[i][2]=resultSet.getString("subjectTeacherSubject");
                i++;
            }
            return subjectTeacher;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
