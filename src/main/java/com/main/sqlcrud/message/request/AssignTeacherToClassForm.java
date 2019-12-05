package com.main.sqlcrud.message.request;

import javax.validation.constraints.NotNull;

public class AssignTeacherToClassForm {

    @NotNull
    private String classId;

    private String oldTeacherNic;

    @NotNull
    private String newTeacherNic;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getNewTeacherNic() {
        return newTeacherNic;
    }

    public void setNewTeacherNic(String newTeacherNic) {
        this.newTeacherNic = newTeacherNic;
    }

    public String getOldTeacherNic() {
        return oldTeacherNic;
    }

    public void setOldTeacherNic(String oldTeacherNic) {
        this.oldTeacherNic = oldTeacherNic;
    }

}