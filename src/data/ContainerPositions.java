package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContainerPositions {
	private Calendar time;
	private int container_id;
	private int stock_row;
	private int stock_col;
	private int stock_height;
	private int train_pos;
	private int train_id;
	private boolean is_on_crane;
	@Override
	public String toString() {
		return dateToString(time) + "\t"
				+ container_id + "\t" + stock_row + "\t"
				+ stock_col + "\t" + stock_height + "\t"
				+ train_pos + "\t" + train_id + "\t"
				+ is_on_crane;
	}
	
	public ContainerPositions(Calendar time, int container_id, int stock_row,
			int stock_col, int stock_height, int train_pos, int train_id,
			boolean is_on_crane) {
		super();
		this.time = time;
		this.container_id = container_id;
		this.stock_row = stock_row;
		this.stock_col = stock_col;
		this.stock_height = stock_height;
		this.train_pos = train_pos;
		this.train_id = train_id;
		this.is_on_crane = is_on_crane;
	}
	
	public static String dateToString(Calendar cal){
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
}
