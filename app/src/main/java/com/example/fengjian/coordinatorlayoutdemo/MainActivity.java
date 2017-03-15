package com.example.fengjian.coordinatorlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private List<View> mViewData;
    private int viewCounts = 3;
    private int itemCounts = 20;
    private TextView mGroupSubmaryView;
    private FloatingActionButton meFabEditView;
    private String mCommonTitle = "话题";
    private String mTopicTitle = "甜菜Baby小组标题";
    private float mTopViewShowRatio = 0.3f;
    private String mSubmary = "星空灿烂无比美丽，激情主播最爱顶顶！亲们，动起你的小小手拉上你的好朋友一起来支持我们可爱帅气的主播–" +
            "顶顶直播间期待您的光临" +
            "场控轻易不动手动手就是送人走。。。不要擦边" +
            "请配合我们的工作星空灿烂无比美丽，激情主播最爱顶顶！亲们，动起你的小小手拉上你的好朋友一起来支持我们可爱帅气的主播–" +
            "谢谢星空灿烂无比美丽，激情主播最爱顶顶！亲们，动起你的小小手拉上你的好朋友一起来支持我们可爱帅气的主播–" +
            "谢谢大家对视频场控工作的支持" +
            "祝大家在直播间和" +
            "3867" +
            "玩的开心！";
    private String[] tabArr = new String[]{
            "tab 1", "tab 2", "tab 3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tl_home_tab);
        mGroupSubmaryView = (TextView) findViewById(R.id.tv_gropu_submary);
        mGroupSubmaryView.setText(mSubmary);
        meFabEditView = (FloatingActionButton) findViewById(R.id.fab_home_edit);
        meFabEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditActivity.startActivity(MainActivity.this);
            }
        });
        mPager = (ViewPager) findViewById(R.id.vp_home);
        HomeViewPagerAdapter adapter = getPagerAdapter();
        mPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mPager);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.home_tool_bar);
        toolbar.setTitle(mCommonTitle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        HomeViewPagerBehavior from = HomeViewPagerBehavior.from(mPager);
        if (from != null) {
            from.setOnDependViewChangedListener(new HomeViewPagerBehavior.DependViewChangedListener() {
                @Override
                public void onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
                    if (dependency != null) {
                        float ratio = Math.abs((float) dependency.getY() / dependency.getHeight());
                        if (ratio > mTopViewShowRatio) {
                            toolbar.setTitle(mSubmary);
                        }else {
                            toolbar.setTitle(mCommonTitle);
                        }
                    }
                }
            });
        }
    }


    private List<View> structureTextViewData() {
        List<View> views = new ArrayList<View>();
        for (int i = 0; i < viewCounts; i++) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setText("Page : " + i);
            tv.setTextSize(30);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.BLACK);
            views.add(tv);
        }
        return views;
    }

    private List<View> structureRecyclerView() {
        List<View> views = new ArrayList<View>();
        for (int i = 0; i < viewCounts; i++) {
            List<String> itemTitles = new ArrayList<String>();
            RecyclerView recyclerView = new RecyclerView(this);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setLayoutParams(params);
            for (int j = 0; j < itemCounts; j++) {
                itemTitles.add(String.format("page : %d, item : %d", i, j));
            }
            views.add(recyclerView);
            recyclerView.setAdapter(new HomeRecyclerAdapter(itemTitles));
        }
        return views;
    }

    private HomeViewPagerAdapter getPagerAdapter() {
        mViewData = structureRecyclerView();
        return new HomeViewPagerAdapter(mViewData, tabArr);
    }

    class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.CurViewHolder> {
        List<String> itemTitles;

        public HomeRecyclerAdapter(List<String> itemTitles) {
            this.itemTitles = itemTitles;
        }

        @Override
        public CurViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = getLayoutInflater().inflate(R.layout.home_item, parent, false);
            return new CurViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(CurViewHolder holder, int position) {
            holder.icon.setImageResource(R.drawable.hone_icon);
            holder.title.setText(itemTitles.get(position));
        }

        @Override
        public int getItemCount() {
            return itemTitles.size();
        }

        public class CurViewHolder extends RecyclerView.ViewHolder {
            private ImageView icon;
            private TextView title;

            public CurViewHolder(View itemView) {
                super(itemView);
                icon = (ImageView) itemView.findViewById(R.id.iv_icon);
                title = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }
    }


    class HomeViewPagerAdapter extends PagerAdapter {
        List<View> viewData;
        String[] tabArr;

        public HomeViewPagerAdapter(List<View> viewData, String[] tabArr) {
            this.viewData = viewData;
            this.tabArr = tabArr;
        }

        @Override
        public int getCount() {
            return viewData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewData.get(position));
            return viewData.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabArr[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
