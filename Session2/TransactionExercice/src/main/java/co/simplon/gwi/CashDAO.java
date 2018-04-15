package co.simplon.gwi;

import java.sql.Connection;
import java.util.List;

public class CashDAO extends DAO<Cash> {

	 
	
	public CashDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cash> getAll() {
		// TODO Auto-generated method stub
						
		return null;
	}

	@Override
	public Cash create(Cash object) {
		// TODO Auto-generated method stub
		//executeUpdateQuery(preparedQuery, param)
		return null;
	}

	@Override
	public Cash read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cash update(Cash object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cash object) {
		// TODO Auto-generated method stub

	}

}
