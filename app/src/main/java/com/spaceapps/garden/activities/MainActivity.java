package com.spaceapps.garden.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.spaceapps.garden.R;
import com.spaceapps.garden.fragments.GardensFragment;
import com.spaceapps.garden.fragments.PlantsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String garden;
    private String plants;
    private String aboutApp;
    private String settings;
    private String market;
    private int fragmentToLoadId;

    private ArrayList<IDrawerItem> drawerItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        garden = getString(R.string.garden);
        plants = getString(R.string.plants);
        aboutApp = getString(R.string.about_app);
        settings = getString(R.string.settings);
        market = getString(R.string.market);
        setupNavigationDrawer();//this method already calls setupDrawerItems()
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root_layout, new GardensFragment())
                    .commit();
        } else {
            if (savedInstanceState.getInt("FragmentToLoad") == 1) {
                Toast.makeText(this, "Fragment Garden", Toast.LENGTH_SHORT).show();
            } else if (savedInstanceState.getInt("FragmentToLoad") == 2) {
                Toast.makeText(this, "Fragment Plants", Toast.LENGTH_SHORT).show();
            }
        }
       /* */

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("FragmentToLoad", fragmentToLoadId);
    }

    private void setupNavigationDrawer() {
        DrawerBuilder drawerBuilder = new DrawerBuilder();

        int header = R.drawable.drawer_image;

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(header)
                //.addProfiles(profile)
                //.withHeaderBackgroundScaleType(ImageView.ScaleType.CENTER_CROP)
                .withSelectionListEnabledForSingleProfile(false)
                .withProfileImagesClickable(false)
                .build();


        drawerBuilder.withActivity(this);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.withAccountHeader(accountHeader);
        //drawerBuilder.withShowDrawerOnFirstLaunch(true);
        drawerBuilder.withActionBarDrawerToggleAnimated(true);
        drawerBuilder.withStickyFooterShadow(false);
        drawerBuilder.withStickyFooterDivider(true);
        drawerBuilder.withSelectedItem(-1);
        drawerBuilder.withHeaderPadding(false);
        setupDrawerItems();
        drawerBuilder.withDrawerItems(drawerItems);
        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                long id = drawerItem.getIdentifier();

                if (id == 1) {
                    fragmentToLoadId = 1;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root_layout, new GardensFragment())
                            .commit();
                } else if (id == 2) {
                    fragmentToLoadId = 2;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root_layout, new PlantsFragment())
                            .commit();
                } else if (id == 3) {
                    // fragmentToLoadId = 3;
                } else if (id == 4) {
                    //fragmentToLoadId = 4;
                    // startActivity(new Intent(MainActivity.this, MessagingActivity.class));
                } else if (id == 5) {
                    //fragmentToLoadId = 5;
                    startActivity(new Intent(MainActivity.this, MessagingActivity.class));
                } else if (id == 6) {
                    //fragmentToLoadId = 6;
                    // startActivity(new Intent(MainActivity.this, MessagingActivity.class));
                } else if (id == 7) {
                    //fragmentToLoadId = 7;
                    // startActivity(new Intent(MainActivity.this, MessagingActivity.class));
                }
                return false;
            }
        });
        drawerBuilder.build();
    }

    private void setupDrawerItems() {
        drawerItems.add(new SectionDrawerItem().withName("Core"));

        drawerItems.add(new PrimaryDrawerItem().withIdentifier(1).withName(garden)
                .withIcon(GoogleMaterial.Icon.gmd_grid_on)
                .withSelectedTextColorRes(R.color.colorAccent)
                .withIconColor(getResources().getColor(R.color.md_blue_grey_500))
                .withSelectedIconColorRes(R.color.md_blue_grey_500));

        drawerItems.add(new PrimaryDrawerItem().withIdentifier(2).withName(plants)
                .withIcon(CommunityMaterial.Icon.cmd_nature)
                .withSelectedTextColorRes(R.color.colorAccent)
                .withIconColor(getResources().getColor(R.color.md_blue_grey_500))
                .withSelectedIconColorRes(R.color.md_blue_grey_500));

        drawerItems.add(new PrimaryDrawerItem().withIdentifier(3).withName(market)
                .withIcon(CommunityMaterial.Icon.cmd_basket)
                .withSelectedTextColorRes(R.color.colorAccent)
                .withIconColor(getResources().getColor(R.color.md_blue_grey_500))
                .withSelectedIconColorRes(R.color.md_blue_grey_500));

        drawerItems.add(new SectionDrawerItem().withName("Personnal"));

        drawerItems.add(new PrimaryDrawerItem().withIdentifier(4).withName("My profile")
                .withIcon(GoogleMaterial.Icon.gmd_account_circle)
                .withSelectedTextColorRes(R.color.colorAccent)
                .withIconColor(getResources().getColor(R.color.md_blue_grey_500))
                .withSelectedIconColorRes(R.color.md_blue_grey_500));
        drawerItems.add(new PrimaryDrawerItem().withIdentifier(5).withName("Mentor chat")
                .withIcon(CommunityMaterial.Icon.cmd_message_text)
                .withSelectedTextColorRes(R.color.colorAccent)
                .withSelectable(false)
                .withIconColor(getResources().getColor(R.color.md_blue_grey_500))
                .withSelectedIconColorRes(R.color.md_blue_grey_500));
        drawerItems.add(new SectionDrawerItem().withName("Others"));

        drawerItems.add(new SecondaryDrawerItem().withName(aboutApp).withIcon(GoogleMaterial.Icon
                .gmd_more_horiz).withIdentifier(6).withIconColor(getResources()
                .getColor(R.color.md_grey_700)).withSelectable(false));

        drawerItems.add(new SecondaryDrawerItem().withName(settings).withIcon(GoogleMaterial
                .Icon.gmd_settings).withIdentifier(7).withIconColor(getResources()
                .getColor(R.color.md_grey_700)).withSelectable(false));

    }

}
