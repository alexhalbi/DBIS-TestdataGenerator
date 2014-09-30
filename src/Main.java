import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import data.ContainerPositions;

public class Main {

	public static void main(String[] args) {
		if (args.length > 3) {
			Calendar d = new GregorianCalendar();
			d.set(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
					Integer.parseInt(args[2]));
			generate(d, 1000000 / 30, Integer.parseInt(args[3]));
		} else if (args.length > 0) {
			Calendar d = new GregorianCalendar();
			d.set(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
					Integer.parseInt(args[2]));
			generate(d, 1000000 / 30, 1);
		} else {
			generate(Calendar.getInstance(), 1000000 / 30, 1);
		}
	}

	public static void generate(Calendar date, int n, int dur) {
		PrintWriter writer;
		for (int d = 0; d < dur; d++) {
			String file = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dir = "genData";
			if (date != null) {
				file = sdf.format(date.getTime());
			}
			try {
				writer = new PrintWriter(dir + "/containerPositions_" + file
						+ ".txt", "UTF-8");
				writer.println("time\tcontainer_id\tstock_row\tstock_col\tstock_height\ttrain_pos\ttrain_id\tis_on_crane");
				for (int i = 0; i < n; i++) {
					if (i % 400 == 0) {
						writer.println("ERROR400");
					} else {
						writer.println(generateCont(date).toString());
					}
				}
				writer.close();
			} catch (FileNotFoundException e) {
				File theDir = new File(dir);
				try {
					theDir.mkdir();
				} catch (SecurityException se) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			date.add(Calendar.DAY_OF_MONTH, 1);
		}

	}

	public static ContainerPositions generateCont(Calendar date) {
		int container_id = (int) (Math.random() * 30);
		int stock_row = -1; // null values
		int stock_col = -1;
		int stock_height = -1;
		int train_pos = -1;
		int train_id = -1;
		boolean is_on_crane = false;

		int hour = randBetween(0, 23); // Hours will be displayed in between 9
										// to 22
		int min = randBetween(0, 59);
		int sec = randBetween(0, 59);
		date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
				date.get(Calendar.DAY_OF_MONTH), hour, min, sec);

		int i = (int) (Math.random() * 3);

		if (i == 0) {
			stock_row = (int) (Math.random() * 5);
			stock_col = (int) (Math.random() * 2);
			stock_height = (int) (Math.random() * 2);
		} else if (i == 1) {
			train_id = (int) (Math.random() * 10);
			train_pos = (int) (Math.random() * 5);
		} else {
			is_on_crane = true;
		}

		return new ContainerPositions(date, container_id, stock_row, stock_col,
				stock_height, train_pos, train_id, is_on_crane);
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
}
