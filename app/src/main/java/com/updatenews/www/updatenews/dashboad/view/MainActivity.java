package com.updatenews.www.updatenews.dashboad.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.updatenews.www.updatenews.DtosBeans.SourcesModel;
import com.updatenews.www.updatenews.R;
import com.updatenews.www.updatenews.dashboad.presenter.CategoryPresenter;
import com.updatenews.www.updatenews.login.view.LoginActivity;
import com.updatenews.www.updatenews.utils.CommonProgressDialog;
import com.updatenews.www.updatenews.utils.ConstantClass;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import static com.updatenews.www.updatenews.utils.ConstantClass.golobalSourcesModelsList;
import static com.updatenews.www.updatenews.utils.ConstantClass.resetChannelList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewsCategoryFragment.OnFragmentInteractionListener, ICategoryView {
    private static long back_pressed;
    private CategoryPresenter presenter;
    private NewsCategoryFragment allChannelFragment;
    public FloatingActionButton fab;
    private Button cancelBtn;
    private Button applyBtn;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new CategoryPresenter(this);
        showProgressDialog();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        TextView userMail = (TextView) hView.findViewById(R.id.user_mail_address);
        TextView userName = (TextView) hView.findViewById(R.id.user_name);
        ImageView imageUser = (ImageView) hView.findViewById(R.id.login_user_image);
        userMail.setText(ConstantClass.USER_DETAIL.getEmail());
        userName.setText(ConstantClass.USER_DETAIL.getDisplayName());
        if (ConstantClass.USER_DETAIL.getPhotoUrl() != null)
            Glide.with(this).load(ConstantClass.USER_DETAIL.getPhotoUrl()).into(imageUser);

     /*   if (ConstantClass.USER_DETAIL != null) {
            for (UserInfo profile : ConstantClass.USER_DETAIL.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();
                //String uid = profile.getUid();
                String name = profile.getDisplayName();
                String email = profile.getEmail();
               // Uri photoUrl = profile.getPhotoUrl();
                userMail.setText(email);
                userName.setText(name);
            }
        }*/
        navigationView.setNavigationItemSelectedListener(this);
        if (ConstantClass.isNetworkAvailable(this))
            presenter.getListOfNewsChannel();
        else
            Toast.makeText(this
                    , getString(R.string.no_internet_connectivity_error),
                    Toast.LENGTH_SHORT).show();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:" + getString(R.string.email_address)));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
                startActivity(Intent.createChooser(intent, "Send email..."));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                back_pressed = System.currentTimeMillis();
                Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            return true;
        }
        if (id == R.id.action_filter) {
            createFilterDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void createFilterDialog() {
        final Dialog dialog = new Dialog(this, R.style.ThemeOverlay_AppCompat_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog);
        dialog.setCancelable(false);

        cancelBtn = dialog.findViewById(R.id.btn_close_filter);
        applyBtn = dialog.findViewById(R.id.btn_submit_filter);
        spinner = dialog.findViewById(R.id.language_spinner);

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = spinner.getSelectedItemPosition();
                String key;
                if (position > 0) key = "de";
                else key = "en";

                addFragment(ConstantClass.resetFilter(golobalSourcesModelsList, key));
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_news) {
            addFragment(golobalSourcesModelsList);
        } else if (id == R.id.nav_science) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Science-and-Nature"));
        } else if (id == R.id.nav_sports) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Sports"));

        } else if (id == R.id.nav_technology) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Technology"));

        } else if (id == R.id.nav_business) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Business"));

        } else if (id == R.id.nav_enterainment) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Entertainment"));

        } else if (id == R.id.nav_general) {
            addFragment(resetChannelList(golobalSourcesModelsList, "General"));

        } else if (id == R.id.nav_music) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Music"));

        } else if (id == R.id.nav_politics) {
            addFragment(resetChannelList(golobalSourcesModelsList, "Politics"));

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, ConstantClass.APP_URL);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_send) {
            ShareAppLinkFragment shareAppLinkFragment = new ShareAppLinkFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, shareAppLinkFragment).commit();
        } else {
            finish();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void showProgressDialog() {
        CommonProgressDialog.showLoader(this);
    }

    @Override
    public void hideProgressDialog() {
        CommonProgressDialog.hideLoader();
    }

    @Override
    public void getChannelList(ArrayList<SourcesModel> sourcesModelsList, boolean isResult) {
        hideProgressDialog();
        golobalSourcesModelsList = new ArrayList<>();
        if (isResult && sourcesModelsList.size() > 0) {
            golobalSourcesModelsList.addAll(sourcesModelsList);
            addFragment(sourcesModelsList);
        }
    }

    void addFragment(ArrayList<SourcesModel> sourcesModelsList) {
        allChannelFragment = new NewsCategoryFragment();
        //allChannelFragment.newInstance(sourcesModelsList);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ConstantClass.SOURCE_MODEL, sourcesModelsList);
        allChannelFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, allChannelFragment).commit();
    }
}