import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import BookList from '@/components/BookList'
import BookItem from '@/components/BookItem'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/books',
      name: 'BookList',
      component: BookList
    },
    {
      path: '/books/:id',
        name: 'BookItem',
        component: BookItem,
      props: true
    }
  ]
})