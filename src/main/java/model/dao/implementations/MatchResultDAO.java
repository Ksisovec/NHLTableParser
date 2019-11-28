package model.dao.implementations;

import model.MatchResult;
import model.dao.GenericDAO;
import model.dao.interfaces.IMatchResultDAO;

import java.util.List;

public class MatchResultDAO extends GenericDAO<MatchResult, Long> implements IMatchResultDAO {

    public MatchResultDAO(){
        setClazz(MatchResult.class);
    }


}
