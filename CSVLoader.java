import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.lang.*;
// import java.util.Date;
// import java.util.List;
// import java.util.StringJoiner;


class CSVLoader {

	private static final 
		String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values});";
	private static final String TABLE_REGEX = "\\$\\{table\\}";
	private static final String KEYS_REGEX = "\\$\\{keys\\}";
	private static final String VALUES_REGEX = "\\$\\{values\\}";

	private Connection connection;
	private char seprator;

	/**
	 * Public constructor to build CSVLoader object with
	 * Connection details. The connection is closed on success
	 * or failure.
	 * @param connection
	 */
	public CSVLoader(Connection connection) {
		this.connection = connection;
		//Set default separator
		this.seprator = ',';
	}
	
	/**
	 * Parse CSV file using OpenCSV library and load in 
	 * given database table. 
	 * @param csvFile Input CSV file
	 * @param tableName Database table name to import data
	 * @param truncateBeforeLoad Truncate the table before inserting 
	 * 			new records.
	 * @throws Exception
	 */
	public void loadCSV(String csvFile, String tableName,
			String[] attributes,boolean truncateBeforeLoad) throws Exception {

		BufferedReader csvReader = null;
		if(null == this.connection) {
			throw new Exception("Not a valid connection.");
		}
		try {
			
            csvReader = new BufferedReader(new FileReader(csvFile));
            

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occured while executing file. "
					+ e.getMessage());
		}

        //String firstrow = csvReader.readLine();
        //System.out.println(SQL_INSERT);

		// String questionmarks = StringUtils.repeat("?,", headerRow.length);
		// questionmarks = (String) questionmarks.subSequence(0, questionmarks
		// 		.length() - 1);

        String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
        //System.out.println(query);
		// query = query
        // 		.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
        StringBuilder questionmark = new StringBuilder();
		StringBuilder attr = new StringBuilder();
		boolean first = true;
        for(int i = 0; i < attributes.length; i++){
			if(first)
				first = false;
			else{
				questionmark.append(",");
				attr.append(",");
			}
            questionmark.append("?");
            attr.append(attributes[i]);
        }
        //System.out.println(questionmark.toString());
        //System.out.println(attr.toString());
        query = query.replaceFirst(KEYS_REGEX, attr.toString());
        query = query.replaceFirst(VALUES_REGEX, questionmark.toString()) ;
        //System.out.println(query);

		System.out.println("Query: " + query);

		String nextLine;
		Connection con = null;
		PreparedStatement ps = null;
		    try {
		  	con = this.connection;
		  	con.setAutoCommit(false);
		  	ps = con.prepareStatement(query);

		 	if(truncateBeforeLoad) {
		 		//delete data from table before loading csv
		 		con.createStatement().execute("DELETE FROM " + tableName + ";");
		 	}

		 	 //final int batchSize = 1000;
		 	 //int count = 0;
             //Date date = null;
             //String nextLine = "";
		 	 while ((nextLine = csvReader.readLine()) != null) {

		 		if (null != nextLine) {
                    //int index = 1;
                    String[] values = nextLine.split(",");
                    //StringJoiner sj = new StringJoiner(",");
                    for(int i = 0; i< values.length; i++){
                        //String string = String.format("\"%s\"", values[i]);
                        //sj.add(string);
                        ps.setString(i+1, values[i]);
                    }
                    //System.out.println(sj.toString());
                    //nextLine = sj.toString();
		// 			for (String string : nextLine) {
		// 				date = DateUtil.convertToDate(string);
		// 				if (null != date) {
		// 					ps.setDate(index++, new java.sql.Date(date
		// 							.getTime()));
		// 				} else {
		// 					ps.setString(index++, string);
		// 				}
        // 			}

                    //ps.setString(index, nextLine);
                    ps.addBatch();
                    //System.out.println(ps.toString());
                    //ps.executeQuery(); 
		 		}
		// 		if (++count % batchSize == 0) {
		// 			ps.executeBatch();
		// 		}
		 	}
		 	ps.executeBatch(); // insert remaining records
            con.commit();
            System.out.printf("Loaded data to table %s\n", tableName);
		 } catch (Exception e) {
		 	con.rollback();
		 	e.printStackTrace();
		 	throw new Exception(
		 			"Error occured while loading data from file to database."
		 					+ e.getMessage());
		 } finally {
		 	if (null != ps)
		 		ps.close();
		// 	if (null != con)
		// 		con.close();

		 	csvReader.close();
		 }
	}

	public char getSeprator() {
		return seprator;
	}

	public void setSeprator(char seprator) {
		this.seprator = seprator;
	}

}