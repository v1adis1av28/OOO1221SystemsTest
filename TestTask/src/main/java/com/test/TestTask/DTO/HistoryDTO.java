package com.test.TestTask.DTO;

import java.util.ArrayList;
import java.util.List;

public class HistoryDTO {
    private int userId;

    public List<DaysIntakeDTO> getDaysIntakeList() {
        return daysIntakeList;
    }

    public void setDaysIntakeList(List<DaysIntakeDTO> daysIntakeList) {
        this.daysIntakeList = daysIntakeList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private List<DaysIntakeDTO> daysIntakeList = new ArrayList<>();

}