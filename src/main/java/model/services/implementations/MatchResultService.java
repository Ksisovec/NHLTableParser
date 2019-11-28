package model.services.implementations;

import model.MatchResult;
import model.dao.implementations.MatchResultDAO;
import model.services.GenericService;
import model.services.interfaces.IMatchResultService;


public class MatchResultService extends GenericService<MatchResult, Long> implements IMatchResultService {

    private MatchResultDAO matchResultDAO = new MatchResultDAO();

    public MatchResultService() {
        matchResultDAO.setClazz(MatchResult.class);
        setDAO(matchResultDAO);
    }


}
