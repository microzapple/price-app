package net.ozielguimaraes.price.helpers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by Oziel on 05/09/2016.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener {
    private Context _context;
    public DateSettings(Context context){ _context = context; }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(_context, "Selected : "+month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_SHORT).show();
    }
}
