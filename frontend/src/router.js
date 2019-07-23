import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import BookList from '@/components/BookList'
import Book from '@/components/Book'

Vue.use(Router);

export default new Router({
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
      name: 'Book',
      component: Book,
      props: true
    }
  ]
})