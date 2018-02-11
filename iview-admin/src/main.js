import Vue from 'vue';
import iView from 'iview';
import {router} from './router/index';
import {appRouter} from './router/router';
import vueResource from 'vue-resource';
import store from './store';
import App from './app.vue';
import '@/locale';
import 'iview/dist/styles/iview.css';
import VueI18n from 'vue-i18n';
import { VirtualScroller } from 'vue-virtual-scroller';

Vue.use(vueResource);
Vue.use(VueI18n);
Vue.use(iView);
Vue.component('virtual-scroller', VirtualScroller);
Vue.http.headers.common['enctype'] = 'application/x-www-form-urlencoded';
Vue.http.headers.common['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.http.headers.common['X-Requested-With'] = 'XMLHttpRequest';

new Vue({
    el: '#app',
    router: router,
    store: store,
    render: h => h(App),
    data: {
        currentPageName: ''
    },
    mounted () {
        this.currentPageName = this.$route.name;
        // 显示打开的页面的列表
        this.$store.commit('setOpenedList');
        this.$store.commit('initCachepage');
        // 权限菜单过滤相关
        this.$store.commit('updateMenulist');
        // iview-admin检查更新
        // util.checkUpdate(this);
    },
    created () {
        let tagsList = [];
        appRouter.map((item) => {
            if (item.children.length <= 1) {
                tagsList.push(item.children[0]);
            } else {
                tagsList.push(...item.children);
            }
        });
        this.$store.commit('setTagsList', tagsList);
    }
});