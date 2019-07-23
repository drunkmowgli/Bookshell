import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import BookList from '@/components/BookList'
import Book from '@/components/Book'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
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