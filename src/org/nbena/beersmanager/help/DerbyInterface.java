package org.nbena.beersmanager.help;

//import java.sql.Connection;
//
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Savepoint;
//import java.sql.Statement;
//import java.util.LinkedList;
//import java.util.Properties;
//
//import org.nbena.beersmanager.coreclasses.Beer;
//import org.nbena.beersmanager.coreclasses.Brewery;
//import org.nbena.beersmanager.coreclasses.Fermentation;
//import org.nbena.beersmanager.coreclasses.Style;

public class DerbyInterface {
	
//	public static enum GROUP_BY_BEER_CLAUSE{BREWERY_FIRST, STYLE_FIRST}
//	public static final String NO_SUBCATEGORY="<no subcategory>";
	
//	private Connection connection;
//
//	public DerbyInterface() throws ClassNotFoundException {
//		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//	}
//	
//	public void connect(String path, String password) throws SQLException{
//		Properties property=new Properties();
//		property.put("dataEncryption", "true");
//		property.put("bootPassword", password);
//		property.put("encryptionAlgorithm", "AES/CBC/NoPadding");
//		connection=DriverManager.getConnection("jdbc:derby:"+path, property);
//		connection.setAutoCommit(false);
//	}
//	
//	public LinkedList<Style> getAllStyles() throws SQLException{
//		LinkedList<Style> list=new LinkedList<Style>();
//	    Statement stat=connection.createStatement();
//	    ResultSet rs=stat.executeQuery("SELECT style_main_name, style_subcategory, fermentation, description FROM style ORDER BY fermentation, style_main_name");
//	    while(rs.next()){
//	    	Style style=new Style();
//	    	style.setStyleMainName(rs.getString(1));
//	    	style.setStyleSubCategory(rs.getString(2));
//	    	style.setFermentation(Fermentation.toFermentation(rs.getString(3)));
//	    	style.setDescription(rs.getString(4));
//	    	list.add(style);
//	    	
//	    }
//	    return list;
//		
//	}
//	
//	public LinkedList<Brewery> getAllBreweries() throws SQLException{
//		LinkedList<Brewery> list=new LinkedList<Brewery>();
//		Statement stat=connection.createStatement();
//		//ResultSet rs=stat.executeQuery("SELECT brewery_name, brewery_town, brewery_country, description, website FROM brewery GROUP BY brewery_country ORDER BY brewery_name");
//		ResultSet rs=stat.executeQuery("SELECT brewery_name, brewery_town, brewery_country, description, website FROM brewery ORDER BY brewery_country, brewery_name");
//		while(rs.next()){
//			Brewery brewery=new Brewery();
//			brewery.setName(rs.getString(1));
//			brewery.setTown(rs.getString(2));
//			brewery.setCountry(rs.getString(3));
//			brewery.setDescription(rs.getString(4));
//			brewery.setWebsite(rs.getString(5));
//			list.add(brewery);
//		}
//		
//		return list;
//	}
//	
//	public boolean breweryExists(Brewery brewery) throws SQLException{
//		String sql="SELCT brewery_name FROM brewery WHERE brewery_name=?;";
//		PreparedStatement pstmt=connection.prepareStatement(sql);
//		pstmt.setString(1, brewery.getName());
//		ResultSet rs=pstmt.executeQuery();
//		return rs.next();
//	}
//	
//	public boolean styleExists(Style style)throws SQLException{
//		String sql="SELECT style_main_name FROM style WHERE style_main_name=?;";
//		PreparedStatement pstmt=connection.prepareStatement(sql);
//		pstmt.setString(1, style.getStyleMainName());
//		ResultSet rs=pstmt.executeQuery();
//		return rs.next();
//	}
//	
//	
////	public void insertNewBrewery(Brewery brewery, boolean isolation) throws SQLException{
////		if(brewery.isComplete())
////			insertNewBreweryComplete(brewery, isolation);
////		else if(brewery.isMinimal())
////			insertNewBreweryMinimal(brewery, isolation);
////	}
//	
//	public void insertNewBreweries(LinkedList<Brewery> list) throws SQLException{
//		Savepoint savepoint=connection.setSavepoint();
//		try{
//			for(Brewery b: list){
//				insertNewBreweryComplete(b, false);
//			}
//			connection.commit();
//		}
//		catch(SQLException e){
//			connection.rollback(savepoint);
//			throw new SQLException(e);
//		}
//	}
//	
//	public void insertNewBreweryComplete(Brewery brewery, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			savepoint=connection.setSavepoint();
//		StringBuilder sb=new StringBuilder();
//		sb.append("INSERT INTO brewery (brewery_name, brewery_town, brewery_country, description, website) ");
//		sb.append(" VALUES (?, ?, ?, ?, ?);");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, brewery.getName());
//		pstmt.setString(2, brewery.getTown());
//		pstmt.setString(3, brewery.getCountry());
//		pstmt.setString(4, brewery.getDescription());
//		pstmt.setString(5, brewery.getWebsite());
//		int res;
//		res=pstmt.executeUpdate();
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new brewery");
//		}
//	}
//	
//	public void insertNewBreweryMinimal(Brewery brewery, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			savepoint=connection.setSavepoint();
//		StringBuilder sb=new StringBuilder();
//		sb.append("INSERT INTO brewery (brewery_name, brewery_town, brewery_country) ");
//		sb.append(" VALUES (?, ?, ?);");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, brewery.getName());
//		pstmt.setString(2, brewery.getTown());
//		pstmt.setString(3, brewery.getCountry());
//		int res;
//		res=pstmt.executeUpdate();
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new brewery");
//		}
//	}
//	
//	public void addWebsiteDescriptionToBrewery(Brewery brewery, boolean isolation) throws SQLException{	
//		Savepoint savepoint=null;
//		PreparedStatement pstmt=null;
//		if(isolation)
//			savepoint=connection.setSavepoint();
//		StringBuilder sb=new StringBuilder();
//		if(brewery.getDescription()!=null && brewery.getWebsite()!=null){
//			sb.append("UPDATE brewery ");
//		    sb.append("SET description=?, website=? ");
//		    sb.append(" WHERE brewery_name=?"); //only name is PK
//		    pstmt=connection.prepareStatement(sb.toString());
//		    pstmt.setString(1, brewery.getDescription());
//		    pstmt.setString(2, brewery.getWebsite());
//		    pstmt.setString(3, brewery.getName());
//		    }
//		else if(brewery.getDescription()==null){
//			sb.append("UPDATE brewery ");
//			sb.append("SET website=? ");
//		    sb.append(" WHERE brewery_name=?"); //only name is PK
//		    pstmt=connection.prepareStatement(sb.toString());
//		    pstmt.setString(1, brewery.getWebsite());
//		    pstmt.setString(2, brewery.getName());
//			}
//		else {
//			sb.append("UPDATE brewery ");
//			sb.append("SET description=? ");
//			sb.append(" WHERE brewery_name=?"); //only name is PK
//			pstmt=connection.prepareStatement(sb.toString());
//		    pstmt.setString(1, brewery.getDescription());
//		    pstmt.setString(2, brewery.getName());
//		   }
//		int res;
//		res=pstmt.executeUpdate();
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new brewery");
//		}
//	}
//	
//	public void insertNewStyleComplete(Style style, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			savepoint=connection.setSavepoint();
//		StringBuilder sb=new StringBuilder();
//		sb.append("INSERT INTO style (style_main_name, style_subcategory, fermentation, description) ");
//		sb.append(" VALUES (?, ?, ?, ?);");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, style.getStyleMainName());
//		pstmt.setString(2, style.getStyleSubCategory());
//		pstmt.setString(3, style.getFermentation().toFirstUpperCase());
//		pstmt.setString(4, style.getDescription());
//		int res;
//		res=pstmt.executeUpdate();
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new style");
//		}		
//	}
//	
//	public void insertNewStyles(LinkedList<Style> list) throws SQLException{
//		Savepoint savepoint=connection.setSavepoint();
//		try{
//			for(Style s: list){
//				insertNewStyleComplete(s, false);
//			}
//			connection.commit();
//		}
//		catch(SQLException e){
//			connection.rollback(savepoint);
//			throw new SQLException(e);
//		}
//	}
//	
//	public void insertNewBeerComplete(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("INSERT INTO beer (beer_name, beer_brewery, beer_style_main_name, beer_style_subcategory, is_tried, place_tried, price, alcool, mark, star, description, color, image)");
//		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getName());
//		pstmt.setString(2, beer.getBrewery().getName());
//		pstmt.setString(3, beer.getStyle().getStyleMainName());
//		pstmt.setString(4, beer.getStyle().getStyleSubCategory());
//		pstmt.setBoolean(5, beer.isTried());
//		pstmt.setString(6, beer.getPlaceTried());
//		pstmt.setDouble(7, beer.getPrice());
//		pstmt.setDouble(8, beer.getAlcool());
//		pstmt.setInt(9, beer.getMark());
//		pstmt.setInt(10, beer.getNumberOfStars());
//		pstmt.setString(11, beer.getDescription());
//		pstmt.setString(12, beer.getColor());
//		pstmt.setBytes(13, beer.getImage() );
//		int res;
//		
//		res=pstmt.executeUpdate();
//		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new beer");
//		}
//	}
//	
//	public void insertNewBeersComplete(LinkedList<Beer> list) throws SQLException{
//		Savepoint savepoint=connection.setSavepoint();
//		try{
//			for(Beer b: list){
//				insertNewBeerComplete(b, false);
//			}
//			connection.commit();
//		}
//		catch(SQLException e){
//			connection.rollback(savepoint);
//			throw new SQLException(e);
//		}		
//	}
//	
//	
//	public void insertNewMinimalBeer(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("INSERT INTO beer (name, beer_style_main_name, beer_style_subcategory, beer_brewery, star, color)");
//		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?);");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getName());
//		pstmt.setString(2, beer.getStyle().getStyleMainName());
//		pstmt.setString(3, beer.getStyle().getStyleSubCategory());
//		pstmt.setString(4, beer.getBrewery().getName());
//		pstmt.setInt(5, beer.getNumberOfStars());	
//		pstmt.setString(6, beer.getColor());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new beer");
//		}	
//	}
//	
//	
//	public void insertNewBeersMinimal(LinkedList<Beer> list) throws SQLException{
//		Savepoint savepoint=connection.setSavepoint();
//		try{
//			for(Beer b: list){
//				insertNewMinimalBeer(b, false);
//			}
//			connection.commit();
//		}
//		catch(SQLException e){
//			connection.rollback(savepoint);
//			throw new SQLException(e);
//		}		
//	}
//	
//	
//	public void insertNewVeryMinimalBeer(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("INSERT INTO beer (name, beer_style_main_name, beer_style_subcategory, beer_brewery)");
//		sb.append(" VALUES (?, ?, ?, ?);");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getName());
//		pstmt.setString(2, beer.getStyle().getStyleMainName());
//		pstmt.setString(3, beer.getStyle().getStyleSubCategory());
//		pstmt.setString(4, beer.getBrewery().getName());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert new beer");
//		}	
//	}
//	
//	public void insertNewVeryMinimalBeers(LinkedList<Beer> list) throws SQLException{
//		Savepoint savepoint=connection.setSavepoint();
//		try{
//			for(Beer b: list){
//				insertNewVeryMinimalBeer(b, false);
//			}
//			connection.commit();
//		}
//		catch(SQLException e){
//			connection.rollback(savepoint);
//			throw new SQLException(e);
//		}		
//	}
//	
//	
//	public void setAssageWithoutColor(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("UPDATE beer");
//		sb.append("SET place_tried=?, ");
//		sb.append("price=?, ");
//		sb.append("alcool=?, ");
//		sb.append("is_tried=TRUE, ");  //remember default false
//		sb.append("mark=?, ");
//		sb.append("star=?, ");
//		sb.append("description=? ");
//		sb.append("WHERE  name=?");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getPlaceTried());
//		pstmt.setDouble(2, beer.getPrice());
//		pstmt.setDouble(3, beer.getAlcool());
//		pstmt.setInt(4, beer.getMark());
//		pstmt.setInt(5, beer.getNumberOfStars());
//		pstmt.setString(6, beer.getDescription());
//		pstmt.setString(7, beer.getName());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert assage");
//		}		
//	}
//	
//	
//	public void setAssageWithColor(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("UPDATE beer");
//		sb.append("SET place_tried=?, ");
//		sb.append("price=?, ");
//		sb.append("alcool=?, ");
//		sb.append("is_tried=TRUE, ");  //remember default false
//		sb.append("mark=?, ");
//		sb.append("star=?, ");
//		sb.append("description=? ");
//		sb.append("color=? ");
//		sb.append("WHERE  name=?");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getPlaceTried());
//		pstmt.setDouble(2, beer.getPrice());
//		pstmt.setDouble(3, beer.getAlcool());
//		pstmt.setInt(4, beer.getMark());
//		pstmt.setInt(5, beer.getNumberOfStars());
//		pstmt.setString(6, beer.getDescription());
//		pstmt.setString(7, beer.getColor());
//		pstmt.setString(8, beer.getName());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert assage");
//		}		
//	}
//	
//	
//	public void setImage(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("UPDATE beer");
//		sb.append("SET image=?, ");
//		sb.append("WHERE  beer_name=?");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setBytes(1, beer.getImage());
//		pstmt.setString(2, beer.getName());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to insert image");
//		}		
//	}
//	
//	public void updateDescriptionBeer(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("UPDATE beer");
//		sb.append("SET description=?, ");
//		sb.append("WHERE  beer_name=?");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getDescription());
//		pstmt.setString(2, beer.getName());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to update description");
//		}			
//	}
//	
//	public void updateDescriptionStyle(Style style, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		//if(breweryExists(beer.getBrewery())==false)
//			//throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("UPDATE style");
//		sb.append("SET description=?, ");
//		sb.append("WHERE  style_main_name=? AND style_subcategory=?");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, style.getDescription());
//		pstmt.setString(2, style.getStyleMainName());
//		pstmt.setString(3, style.getStyleSubCategory());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to update description");
//		}		
//	}
//
//	
//	public void changeBeerStyle(Beer beer, boolean isolation) throws SQLException{
//		Savepoint savepoint=null;
//		if(isolation)
//			connection.setSavepoint();
//		
//		if(breweryExists(beer.getBrewery())==false)
//			throw new SQLException("Brewery '"+beer.getBrewery().getName()+"' not found");
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("UPDATE beer");
//		sb.append("SET beer_style_main_name=?, ");
//		sb.append("beer_style_subcategory=? ");
//		sb.append("WHERE  beer_name=?");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, beer.getStyle().getStyleMainName());
//		pstmt.setString(2, beer.getStyle().getStyleSubCategory());
//		pstmt.setString(3, beer.getName());
//		int res;	
//		res=pstmt.executeUpdate();		
//		if(res>=0){
//			if(isolation){
//				connection.commit();
//			}
//		}
//		else{
//			if(isolation){
//				connection.rollback(savepoint);
//			}
//			throw new SQLException("Fail to update description");
//		}			
//	}
//	
//	
//	
//	LinkedList<Beer> queryAllBeers(GROUP_BY_BEER_CLAUSE clause) throws SQLException{
//		LinkedList<Beer> beers=new LinkedList<Beer>();
//		StringBuilder sb=new StringBuilder();
//		sb.append("SELECT beer_name, beer_brewery, beer_style_main_name, beer_style_subcategory, is_tried, price,");
//		sb.append("alcool, mark, star, beer.description, color, image, place_tried, ");
//		sb.append("brewery_name, brewery_town, brewery_country, brewery_website, brewery.description, ");  //14-from
//		sb.append("style_main_name, style_subcategory, style.description, fermentation "); //19-from
//		sb.append("FROM beer, brewery, style");
//		sb.append("WHERE beer.beer_style_main_name=style.style_main_name AND ");
//		sb.append("beer.beer_style_subcategory=style.style_subcategory AND ");
//		sb.append("beer.beer_brewery_name=brewery.brewery_name ");
//		if(clause==GROUP_BY_BEER_CLAUSE.STYLE_FIRST)
//		    //sb.append("GROUP BY fermentation, style_main_name, style_subcategory, beer.brewery_name ORDER BY beer_name");
//			sb.append("ORDER BY fermentation, style_main_name, style_subcategory, beer.brewery_name, beer_name");
//		else /*(clause==GROUP_BY_BEER_CLAUSE.BREWERY_FIRST)*/
//			//sb.append("GROUP BY beer.brewery_name, fermentation, style_main_name, style_subcategory ORDER BY beer_name");
//			sb.append("ORDER BY beer.brewery_name, fermentation, style_main_name, style_subcategory, beer_name");
//		Statement stat=connection.createStatement();
//		ResultSet rs=stat.executeQuery(sb.toString());
//		while(rs.next()){
//			Beer beer=new Beer();
//			Brewery brewery=new Brewery();
//			Style style=new Style();
//			
//			brewery.setName(rs.getString(14));
//			brewery.setTown(rs.getString(15));
//			brewery.setCountry(rs.getString(16));
//			brewery.setWebsite(rs.getString(17));
//			brewery.setDescription(rs.getString(18));
//			
//			style.setStyleMainName(rs.getString(3));
//			style.setStyleSubCategory(rs.getString(4));
//			style.setDescription(rs.getString(21));
//			style.setFermentation(Fermentation.toFermentation(rs.getString(22)));
//			
//			beer.setName(rs.getString(1));
//			beer.setBrewery(brewery);
//			beer.setStyle(style);
//			beer.setTried(rs.getBoolean(5));
//			beer.setAlcool(rs.getDouble(6));
//			beer.setMark(rs.getInt(7));
//			beer.setNumberOfStars(rs.getInt(8));
//			beer.setDescription(rs.getString(9));
//			beer.setColor(rs.getString(10));
//			beer.setImage(rs.getBytes(11));
//			beer.setPlaceTried(rs.getString(12));
//			
//			beers.add(beer);
//		}
//		
//		return beers;
//	}
//	
//	
//	//brewery-param is not queried
//	LinkedList<Beer> queryAllBrewerysBeer(Brewery breweryCheck) throws SQLException{
//		LinkedList<Beer> beers=new LinkedList<Beer>();
//		StringBuilder sb=new StringBuilder();
//		sb.append("SELECT beer_name, beer_brewery, beer_style_main_name, beer_style_subcategory, is_tried, price,");
//		sb.append("alcool, mark, star, beer.description, color, image, place_tried, ");
//		//sb.append("brewery_name, brewery_town, brewery_country, brewery_website, brewery.description, ");  //14-from
//		sb.append("style_main_name, style_subcategory, style.description, fermentation "); //14-from
//		sb.append("FROM beer, brewery, style");
//		sb.append("WHERE beer.beer_style_main_name=style.style_main_name AND ");
//		sb.append("beer.beer_style_subcategory=style.style_subcategory AND ");
//		sb.append("beer.beer_brewery_name=brewery.brewery_name AND");
//		sb.append("beer.beer_brewery_name=?");
//		//sb.append("GROUP BY fermentation, style_main_name, style_subcategory, beer.brewery_name ORDER BY beer_name");
//		sb.append("ORDER BY fermentation, style_main_name, style_subcategory, beer.brewery_name, beer_name");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, breweryCheck.getName());
//		ResultSet rs=pstmt.executeQuery(sb.toString());
//		while(rs.next()){
//			Beer beer=new Beer();
//			//Brewery brewery=new Brewery();
//			Style style=new Style();
//			
//			/*
//			brewery.setName(rs.getString(14));
//			brewery.setTown(rs.getString(15));
//			brewery.setCountry(rs.getString(16));
//			brewery.setWebsite(rs.getString(17));
//			brewery.setDescription(rs.getString(18));
//			*/
//			
//			style.setStyleMainName(rs.getString(3));
//			style.setStyleSubCategory(rs.getString(4));
//			style.setDescription(rs.getString(16));
//			style.setFermentation(Fermentation.toFermentation(rs.getString(17)));
//			
//			beer.setName(rs.getString(1));
//			beer.setBrewery(breweryCheck);
//			beer.setStyle(style);
//			beer.setTried(rs.getBoolean(5));
//			beer.setAlcool(rs.getDouble(6));
//			beer.setMark(rs.getInt(7));
//			beer.setNumberOfStars(rs.getInt(8));
//			beer.setDescription(rs.getString(9));
//			beer.setColor(rs.getString(10));
//			beer.setImage(rs.getBytes(11));
//			beer.setPlaceTried(rs.getString(12));
//			
//			beers.add(beer);
//		}
//		
//		return beers;
//	}
//	
//	
//	LinkedList<Beer> queryAllGroupByBeerFermentation(Fermentation fermentation, GROUP_BY_BEER_CLAUSE clause) throws SQLException{
//		LinkedList<Beer> beers=new LinkedList<Beer>();
//		StringBuilder sb=new StringBuilder();
//		sb.append("SELECT beer_name, beer_brewery, beer_style_main_name, beer_style_subcategory, is_tried, price,");
//		sb.append("alcool, mark, star, beer.description, color, image, place_tried, ");
//		sb.append("brewery_name, brewery_town, brewery_country, brewery_website, brewery.description, ");  //14-from
//		sb.append("style_main_name, style_subcategory, style.description, fermentation "); //19-from
//		sb.append("FROM beer, brewery, style");
//		sb.append("WHERE beer.beer_style_main_name=style.style_main_name AND ");
//		sb.append("beer.beer_style_subcategory=style.style_subcategory AND ");
//		sb.append("beer.beer_brewery_name=brewery.brewery_name AND");
//		sb.append("style.fermentation=?");
//		if(clause==GROUP_BY_BEER_CLAUSE.STYLE_FIRST)
//		    //sb.append("GROUP BY fermentation, style_main_name, style_subcategory, beer.brewery_name ORDER BY beer_name");
//			sb.append("ORDER BY style_main_name, style_subcategory, beer.brewery_name, beer_name");
//		else /*(clause==GROUP_BY_BEER_CLAUSE.BREWERY_FIRST)*/
//			//sb.append("GROUP BY beer.brewery_name, fermentation, style_main_name, style_subcategory ORDER BY beer_name");
//			sb.append("ORDER BY beer.brewery_name, fermentation, style_main_name, style_subcategory, beer_name");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, Fermentation.toFirstUpperCase(fermentation));
//		ResultSet rs=pstmt.executeQuery(sb.toString());
//		while(rs.next()){
//			Beer beer=new Beer();
//			Brewery brewery=new Brewery();
//			Style style=new Style();
//			
//			brewery.setName(rs.getString(14));
//			brewery.setTown(rs.getString(15));
//			brewery.setCountry(rs.getString(16));
//			brewery.setWebsite(rs.getString(17));
//			brewery.setDescription(rs.getString(18));
//			
//			style.setStyleMainName(rs.getString(3));
//			style.setStyleSubCategory(rs.getString(4));
//			style.setDescription(rs.getString(21));
//			style.setFermentation(Fermentation.toFermentation(rs.getString(22)));
//			
//			beer.setName(rs.getString(1));
//			beer.setBrewery(brewery);
//			beer.setStyle(style);
//			beer.setTried(rs.getBoolean(5));
//			beer.setAlcool(rs.getDouble(6));
//			beer.setMark(rs.getInt(7));
//			beer.setNumberOfStars(rs.getInt(8));
//			beer.setDescription(rs.getString(9));
//			beer.setColor(rs.getString(10));
//			beer.setImage(rs.getBytes(11));
//			beer.setPlaceTried(rs.getString(12));
//			
//			beers.add(beer);
//		}
//		
//		return beers;
//	}
//	
//	
//	LinkedList<Beer> queryAllGroupByBeerStyleSpecific(Style checkStyle, GROUP_BY_BEER_CLAUSE clause, boolean checkSubcategory) throws SQLException{
//		LinkedList<Beer> beers=new LinkedList<Beer>();
//		StringBuilder sb=new StringBuilder();
//		sb.append("SELECT beer_name, beer_brewery, beer_style_main_name, beer_style_subcategory, is_tried, price,");
//		sb.append("alcool, mark, star, beer.description, color, image, place_tried, ");
//		sb.append("brewery_name, brewery_town, brewery_country, brewery_website, brewery.description, ");  //14-from
//		sb.append("style_main_name, style_subcategory, style.description, fermentation "); //19-from
//		sb.append("FROM beer, brewery, style");
//		sb.append("WHERE beer.beer_style_main_name=style.style_main_name AND ");
//		sb.append("beer.beer_style_subcategory=style.style_subcategory AND ");
//		sb.append("beer.beer_brewery_name=brewery.brewery_name AND");
//		sb.append("style_main_name=?");
//		if(checkSubcategory)
//			sb.append("AND style_subcategory=?");
//		if(clause==GROUP_BY_BEER_CLAUSE.STYLE_FIRST)
//		    //sb.append("GROUP BY fermentation, style_main_name, style_subcategory, beer.brewery_name ORDER BY beer_name");
//			sb.append("ORDER BY fermentation, style_main_name, style_subcategory, beer.brewery_name, beer_name");
//		else /*(clause==GROUP_BY_BEER_CLAUSE.BREWERY_FIRST)*/
//			//sb.append("GROUP BY beer.brewery_name, fermentation, style_main_name, style_subcategory ORDER BY beer_name");
//			sb.append("ORDER BY beer.brewery_name, fermentation, style_main_name, style_subcategory, beer_name");
//		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
//		pstmt.setString(1, checkStyle.getStyleMainName());
//		if(checkSubcategory)
//		    pstmt.setString(2, checkStyle.getStyleSubCategory());
//		ResultSet rs=pstmt.executeQuery(sb.toString());
//		while(rs.next()){
//			Beer beer=new Beer();
//			Brewery brewery=new Brewery();
//			Style style=new Style();
//			
//			brewery.setName(rs.getString(14));
//			brewery.setTown(rs.getString(15));
//			brewery.setCountry(rs.getString(16));
//			brewery.setWebsite(rs.getString(17));
//			brewery.setDescription(rs.getString(18));
//			
//			style.setStyleMainName(rs.getString(3));
//			style.setStyleSubCategory(rs.getString(4));
//			style.setDescription(rs.getString(21));
//			style.setFermentation(Fermentation.toFermentation(rs.getString(22)));
//			
//			beer.setName(rs.getString(1));
//			beer.setBrewery(brewery);
//			beer.setStyle(style);
//			beer.setTried(rs.getBoolean(5));
//			beer.setAlcool(rs.getDouble(6));
//			beer.setMark(rs.getInt(7));
//			beer.setNumberOfStars(rs.getInt(8));
//			beer.setDescription(rs.getString(9));
//			beer.setColor(rs.getString(10));
//			beer.setImage(rs.getBytes(11));
//			beer.setPlaceTried(rs.getString(12));
//			
//			beers.add(beer);
//		}
//		
//		return beers;
//	}
//	

}
