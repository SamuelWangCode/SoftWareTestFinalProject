import Vue from 'vue'
import Router from 'vue-router'
import LandingPage from '@/components/LandingPage'
import Home from '@/components/Home'
import Search from '@/components/Search'
import APersonalInfo from '@/components/account/PersonalInformation'
import APersonalFollows from '@/components/account/MyFollows'
import AGuide from '@/components/account/MyGuide'
import SearchUsers from '@/components/SearchUser'
import SearchChampion from '@/components/SearchChampion'
import ChampionInfo from '@/components/ChampionInfo'
import OPersonalInfo from '@/components/oaccount/OPersonalInformation'
import OGuide from '@/components/oaccount/OGuide'
import SearchGuide from '@/components/SearchGuide'
import GuideInfo from '@/components/GuideInfo'
import PostGuide from '@/components/PostGuide'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/landingpage',
      name: 'LandingPage',
      component: LandingPage
    },
    {
      path: '/home',
      name: 'Homepage',
      component: Home
    },
    {
      path: '/search',
      name: 'Search',
      component: Search
    },
    {
      path: '/personalinfo',
      name: 'APersonalInfo',
      component: APersonalInfo
    },
    {
      path: '/myfollows',
      name: 'APersonalFollows',
      component: APersonalFollows
    },
    {
      path: '/searchuser',
      name: 'SearchUsers',
      component: SearchUsers
    },
    {
      path: '/searchchamp',
      name: 'SearchChampion',
      component: SearchChampion
    },
    {
      path: '/championinfo',
      name: 'ChampionInfo',
      component: ChampionInfo
    },
    {
      path: '/opersonalinfo',
      name: 'OPersonalInfo',
      component: OPersonalInfo
    },
    {
      path: '/myguide',
      name: 'AGuide',
      component: AGuide
    },
    {
      path: '/oguide',
      name: 'OGuide',
      component: OGuide
    },
    {
      path: '/searchguide',
      name: 'SearchGuide',
      component: SearchGuide
    },
    {
      path: '/guideinfo',
      name: 'GuideInfo',
      component: GuideInfo
    },
    {
      path: '/postguide',
      name: 'PostGuide',
      component: PostGuide
    },

  ]
})
