package model.dao.implementations;

import model.BookValue;
import model.dao.GenericDAO;
import model.dao.interfaces.IBookValueDAO;

public class BookValueDAO extends GenericDAO<BookValue, Long> implements IBookValueDAO {
    public BookValueDAO(){
        setClazz(BookValue.class);
    }


}
