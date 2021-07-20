package sg.edu.rp.c346.id20014518.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShow;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.radioGroupStars);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnShow);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataTitle = etTitle.getText().toString();
                String dataSingers = etSingers.getText().toString();
                int dataYear = etYear.getId();
                int dataStars = rgStars.getCheckedRadioButtonId();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(dataTitle,dataSingers,dataYear,dataStars);

                if (inserted_id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

    }

    private int getStars(){
        int stars = 1;
        if (rgStars.getCheckedRadioButtonId() == R.id.radio1) {
            stars = 1;
        }
        else if (rgStars.getCheckedRadioButtonId() == R.id.radio2) {
            stars = 2;
        }
        else if (rgStars.getCheckedRadioButtonId() == R.id.radio3) {
            stars = 3;
        }
        else if (rgStars.getCheckedRadioButtonId() == R.id.radio4) {
            stars = 4;
        }
        else if (rgStars.getCheckedRadioButtonId() == R.id.radio5) {
            stars = 5;
        }
        return stars;
    }
}
