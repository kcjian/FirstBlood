package com.liuxd.firstblood.ui.news;

import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.ui.base.BaseModel;
import com.liuxd.firstblood.ui.base.BasePresenter;
import com.liuxd.firstblood.ui.base.BaseView;

import java.util.List;

/**
 * Created by Liuxd on 2016/11/22 11:03.
 */

public class NewsListContract {

    interface View extends BaseView{
        void showNews(List<News.NewsBody> data);
    }

     interface Presenter extends BasePresenter{
        void loadNews(String type);
    }

    interface Model extends BaseModel {
        void loadNews(String type);
    }
}
