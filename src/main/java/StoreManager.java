//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Component;
import javax.swing.JFileChooser;

public class StoreManager {
    public static final String DBMS_SQ_LITE = "SQLite";
    public static final String DB_FILE = "store.db";
    IDataAdapter adapter = null;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null) {
            String dbfile = "store.db";
            if (dbfile.length() == 0) {
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog((Component)null) == 0) {
                    dbfile = fc.getSelectedFile().getAbsolutePath();
                }
            }

            instance = new StoreManager("SQLite", dbfile);
        }

        return instance;
    }

    private StoreManager(String dbms, String dbfile) {
        if (dbms.equals("Oracle")) {
            this.adapter = new OracleDataAdapter();
        } else if (dbms.equals("SQLite")) {
            this.adapter = new SQLiteDataAdapter();
        }

        this.adapter.connect(dbfile);
    }

    public IDataAdapter getDataAdapter() {
        return this.adapter;
    }

    public void setDataAdapter(IDataAdapter a) {
        this.adapter = a;
    }

    public void run() {
        MainUI ui = new MainUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Hello class!");
        getInstance().run();
    }
}
