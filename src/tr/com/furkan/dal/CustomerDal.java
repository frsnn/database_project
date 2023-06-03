package tr.com.furkan.dal;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tr.com.furkan.core.ObjectHelper;
import tr.com.furkan.interfaces.IDataAccessLayer;
import tr.com.furkan.type.Customer;

public class CustomerDal extends ObjectHelper implements IDataAccessLayer<Customer> {

	@Override
	public void insert(Customer contract) {
			Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT customer (adi, soyadi) VALUES('"+contract.getName()+"', '"+contract.getSurname() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Customer contract) {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE customer SET adi='"+contract.getName()+"' , soyadi='"+contract.getSurname()+"'"
					+ " WHERE id="+contract.getId());
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Customer contract) {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM customer WHERE id="+contract.getId());
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Customer> getList() {
		List<Customer> dataContract = new ArrayList<Customer>();
		
		Connection connection =getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT *FROM customer");
			while(rs.next()) {
				
				Customer contract = new Customer();
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("adi"));
				contract.setSurname(rs.getString("soyadi"));
				
				dataContract.add(contract);
				
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}

	@Override
	public Customer getById(int id) {
		Customer contract = new Customer();
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE id=" + id);
			while(rs.next()) {
				
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("adi"));
				contract.setSurname(rs.getString("soyadi"));
				
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return contract;
	}
	public List<Customer> getSearch(String name){
		List<Customer> dataContract = new ArrayList<Customer>();
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE adi LIKE '%"+name+"%'");
			while(rs.next()) {
				
				Customer contract = new Customer();
				
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("name"));
				contract.setSurname(rs.getString("soyadi"));
				
				dataContract.add(contract);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dataContract;
	}

}
