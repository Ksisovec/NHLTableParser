package model.services.implementations;

import model.BookValue;
import model.dao.implementations.BookValueDAO;
import model.services.GenericService;
import model.services.interfaces.IBookValueService;


public class BookValueService extends GenericService<BookValue, Long> implements IBookValueService {

    private BookValueDAO bookValueDAO = new BookValueDAO();

    public BookValueService() {
        bookValueDAO.setClazz(BookValue.class);
        setDAO(bookValueDAO);
    }


}
