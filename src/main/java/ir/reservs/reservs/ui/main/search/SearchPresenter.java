package ir.reservs.reservs.ui.main.search;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;

import java.util.Calendar;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter, DateSetListener {

    private SearchContract.View view;
    private AlertDialog.Builder adb;
    private AlertDialog ad;
    private DatePicker.Builder datePickerBuilder;
    //private FragmentManager fragmentManager;
    private CharSequence[] cities;
    private int selectedCity = -1;
    private CharSequence[] locations;
    private int selectedLocation = -1;

    @Inject
    public SearchPresenter(AlertDialog.Builder alertDialogBuilder,
                           DatePicker.Builder datePickerBuilder) {
        this.adb = alertDialogBuilder;
        this.datePickerBuilder = datePickerBuilder;
       // this.fragmentManager = fragmentManager;
    }

    @Override
    public void onAttach(SearchContract.View view) {

        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
        //fragmentManager = null;
        datePickerBuilder = null;
        if (ad != null)
            ad.dismiss();
        ad = null;
        adb = null;
    }

    @Override
    public void getDate() {
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        maxDate.set(Calendar.MONTH, maxDate.get(Calendar.MONTH) + 1);
        datePickerBuilder.id(1)
                .minDate(minDate)
                .maxDate(maxDate)
                .date(minDate)
                .build(this);
              //  .show(fragmentManager, "date");
    }

    @Override
    public void getCity() {
        cities = new CharSequence[]{"گتوند", "شوشتر", "دزفول", "اهواز"};
        adb.setSingleChoiceItems(cities, 0, (d, n) -> {
            selectedCity = n;
            //view.setCity(cities[n].toString());
            d.dismiss();
        });
        adb.setTitle("انتخاب شهر");
        ad = adb.create();
        ad.show();
    }

    @Override
    public void getLocation() {
        if (selectedCity == -1) {
            view.onError("ابتدا شهر خود را انتخاب کنید.");
            return;
        }
        locations = new CharSequence[]{"سالن شماره دو", "سالن شماره سه", "چمن طبیعی"};
        adb.setSingleChoiceItems(locations, 0, (d, n) -> {
            selectedLocation = n;
            view.setLocation(locations[n].toString());
            d.dismiss();
        });
        adb.setTitle("انتخاب مکان");
        ad = adb.create();
        ad.show();
    }

    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        view.setDate(year, month, day);
    }
}