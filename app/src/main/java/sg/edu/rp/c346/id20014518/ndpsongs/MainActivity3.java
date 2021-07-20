package sg.edu.rp.c346.id20014518.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity3 extends AppCompatActivity {
    EditText etSongID, etTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    Button btnUpdate, btnDelete, btnCancel;
    Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etSongID = findViewById(R.id.etSongID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.radioGroupStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");
        etSongID.setText(String.valueOf(song.get_Id()));
        etTitle.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(song.getYear());

        int stars = song.getStar();
        if (stars == 1)
            radio1.setChecked(true);
        if (stars == 2)
            radio2.setChecked(true);
        if (stars == 3)
            radio3.setChecked(true);
        if (stars == 4)
            radio4.setChecked(true);
        if (stars == 5)
            radio5.setChecked(true);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setTitle(etTitle.getText().toString());
                song.setSingers(etSingers.getText().toString());
                song.setYear(Integer.parseInt(etYear.getText().toString()));
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.updateSong(song);
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteSong(song.get_Id());

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}