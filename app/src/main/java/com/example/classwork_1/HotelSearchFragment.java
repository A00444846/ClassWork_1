package com.example.classwork_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    TextView titleTextView;
    DatePicker checkInDatePicker, checkOutDatePicker;
    EditText noOfGuestEditText, guestNameEditText;
    Button bookButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hotel_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        titleTextView = view.findViewById(R.id.title_text_view);
        titleTextView.setText(R.string.welcome_text);

        checkInDatePicker = view.findViewById(R.id.check_in_date_picker);
        checkOutDatePicker = view.findViewById(R.id.check_out_date_picker);
        noOfGuestEditText = view.findViewById(R.id.no_of_guest_edit_text);
        guestNameEditText = view.findViewById(R.id.your_name_edit_text);


        bookButton = view.findViewById(R.id.book_button);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkInDate = getDateFromDatePicker(checkInDatePicker);
                String checkOutDate = getDateFromDatePicker(checkOutDatePicker);

                String noOfGuest = noOfGuestEditText.getText().toString();
                String guestName = guestNameEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("check_in_date", checkInDate);
                bundle.putString("check_out_date", checkOutDate);
                bundle.putString("no_of_guest", noOfGuest);
                bundle.putString("guest_name", guestName);


                HotelListFragment hotelListFragment = new HotelListFragment();
                hotelListFragment.setArguments(bundle);


                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, hotelListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    // conversion of datepicker to string
    private String getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return dateFormat.format(calendar.getTime());
    }
}
