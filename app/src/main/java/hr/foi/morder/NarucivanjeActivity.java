package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NarucivanjeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.narucivanje);
        FragmentManager fm = getSupportFragmentManager();
        final NarucivanjeFragment fragment = (NarucivanjeFragment)fm.findFragmentById(R.id.fragmentNarucivanje);
        fragment.loadLastArticles();
        fragment.dohvatiIdNarudzbe();
        fragment.dohvatiKategorije();

        navigation = findViewById(R.id.nv);
        setupDrawerContent(navigation);
        navigation.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.kosarica:
                Intent intent = new Intent(this, KosaricaActivity.class);
                startActivity(intent);
                break;

            case R.id.pocetna:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
        }
        return true;
    }

    private void setupDrawerContent(NavigationView nv) {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerView(item);
                return true;
            }
        });
    }

    private void selectDrawerView(MenuItem item) {

    }
}
