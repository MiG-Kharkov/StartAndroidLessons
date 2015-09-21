package ua.com.webtuning.logandmess;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MENU_COLOR_RED = 1;
    private static final int MENU_COLOR_GREEN = 2;
    private static final int MENU_COLOR_BLUE = 3;
    private static final int MENU_SIZE_22 = 4;
    private static final int MENU_SIZE_26 = 5;
    private static final int MENU_SIZE_30 = 6;
    private final String DEBAG_MAIN_TAG = MainActivity.class.getSimpleName();
    TextView tvOut;
    TextView tvSize;
    Button btnOk;
    Button btnCancel;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOK);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        tvSize = (TextView) findViewById(R.id.tvSize);

        tvOut.setOnCreateContextMenuListener(this);
        tvSize.setOnCreateContextMenuListener(this);

        Log.d(DEBAG_MAIN_TAG, "Creat Application");
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.tvOut:
                menu.add(0, MENU_COLOR_RED, 1, "Red");
                menu.add(0, MENU_COLOR_GREEN, 2, "Green");
                menu.add(0, MENU_COLOR_BLUE, 3, "Blue");
                break;
            case R.id.tvSize:
                menu.add(0, MENU_SIZE_22, 1, "22");
                menu.add(0, MENU_SIZE_26, 1, "26");
                menu.add(0, MENU_SIZE_30, 1, "30");
                break;
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COLOR_RED:
                tvOut.setTextColor(Color.RED);
                break;
            case MENU_COLOR_BLUE:
                tvOut.setTextColor(Color.BLUE);
                break;
            case MENU_COLOR_GREEN:
                tvOut.setTextColor(Color.GREEN);
                break;
            // пункты меню для tvSize
            case MENU_SIZE_22:
                tvSize.setTextSize(22);
                tvSize.setText("Text size = 22");
                break;
            case MENU_SIZE_26:
                tvSize.setTextSize(26);
                tvSize.setText("Text size = 26");
                break;
            case MENU_SIZE_30:
                tvSize.setTextSize(30);
                tvSize.setText("Text size = 30");
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Prepare the Screen's standard options menu to be displayed.  This is
     * called right before the menu is shown, every time it is shown.  You can
     * use this method to efficiently enable/disable items or otherwise
     * dynamically modify the contents.
     * <p>
     * <p>The default implementation updates the system menu items based on the
     * activity's state.  Deriving classes should always call through to the
     * base class implementation.
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean isChecked = checkBox.isChecked();
        //Найти пункт меню по id ресурса
        // getItem ищет по номеру порядковуму в списке размером в size()
        menu.findItem(R.id.list1).setVisible(isChecked);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(this, "Pressed " + item.getTitle(), Toast.LENGTH_LONG).show();
        tvOut.setText("ID=" + id + " Title= " + item.getTitle());
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                tvOut.setText("Button OK");
                Toast.makeText(this, "Pressed button OK", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancel:
                tvOut.setText("Button Cancel");
                Toast.makeText(this, "Pressed button Cancel", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
