package com.spaceapps.garden.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.spaceapps.garden.R;
import com.spaceapps.garden.fragments.GardensFragment;
import com.spaceapps.garden.fragments.PlantsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String garden;
    private String plants;
    private String calendar;

    private ArrayList<IDrawerItem> drawerItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        garden = getString(R.string.garden);
        plants = getString(R.string.plants);
        calendar = getString(R.string.calendar);
        setupNavigationDrawer();//this method already calls setupDrawerItems()
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root_layout, new GardensFragment())
                .commit();

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
        setupDrawerItems();
        drawerBuilder.withDrawerItems(drawerItems);
         /*  drawerBuilder.addStickyDrawerItems(
                new SecondaryDrawerItem().withName(tutoriel).withIcon(GoogleMaterial.Icon
                        .gmd_help_outline).withIdentifier(7).withIconColor(getResources()
                        .getColor(R.color.md_grey_700)).withSelectable(false),
                new SecondaryDrawerItem().withName(aboutApp).withIcon(GoogleMaterial.Icon
                        .gmd_more_horiz).withIdentifier(80).withIconColor(getResources()
                        .getColor(R.color.md_grey_700)).withSelectable(false))
             new SecondaryDrawerItem().withName(settings).withIcon(GoogleMaterial
                        .Icon.gmd_settings).withIdentifier(8).withIconColor(getResources()
                        .getColor(R.color.md_grey_700)).withSelectable(false))*/
        ;
        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                long id = drawerItem.getIdentifier();

                if (id == 1) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root_layout, new GardensFragment())
                            .commit();
                } else if (id == 2) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root_layout, new PlantsFragment())
                            .commit();
                } else if (id == 3) {
                } else if (id == 5) {
                    // startActivity(new Intent(MainActivity.this, AboutActivity.class));
                }
                return false;
            }
        });
        drawerBuilder.build();
    }

    private void setupDrawerItems() {

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

        drawerItems.add(new PrimaryDrawerItem().withIdentifier(3).withName(calendar)
                .withIcon(CommunityMaterial.Icon.cmd_calendar_multiple)
                .withSelectedTextColorRes(R.color.colorAccent)
                .withIconColor(getResources().getColor(R.color.md_blue_grey_500))
                .withSelectedIconColorRes(R.color.md_blue_grey_500));
    }

}
