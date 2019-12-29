package com.team.ghana;

import com.team.ghana.strategicClasses.SearchByBusinessUnit;
import com.team.ghana.strategicClasses.SearchByDepartment;
import com.team.ghana.strategicClasses.SearchByUnit;

public class Factory {

    public static SearchStrategy choose(String criteria){

        switch(criteria.toLowerCase()){
            case "businessunit":
                return new SearchByBusinessUnit();
            case "department":
                return new SearchByDepartment();
            case "unit":
                return new SearchByUnit();
        }
        return new SearchByBusinessUnit();
    }
}
