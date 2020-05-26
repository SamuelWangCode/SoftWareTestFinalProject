<template>

<div class="page-has-left-panels">
<!-- Fixed Sidebar Left -->

<div id="mypreloader">
<loading></loading>
</div>


<headline></headline>


<div class="header-spacer header-spacer-small"></div>


<div class="main-header">
	<div ></div>
	<div class="content-bg-wrap bg-badges">
		<div class="row">
			<div class="col col-lg-8 m-auto col-md-8 col-sm-12 col-12">
				<div class="main-header-content">
					<h1>Search The Champion</h1>
					<p>Here you can search all champions in league of legends!!</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ... end Main Header Account -->


<!-- Your Account Personal Information -->

<div class="container">
	<div class="row">
		<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="ui-block">
				<div class="ui-block-title">
					<h6 class="title">Champion</h6>
                                    <!-- <div class="w-search"> -->
					<!-- <div class="form-group with-button">
						<input class="form-control" type="text" v-model="championname" placeholder="Search the champions...">
						<button @click="SearchChamp">
							<i class="fa fa-search-plus fa-2x" aria-hidden="true"></i>
						</button>
					</div> -->
							<fieldset class="form-group" style="width:250px;float:right">
								<select class="selectpicker form-control" v-model="finalname">
									<option value="All">All</option>
									<option value="Assassin">Assassin</option>
									<option value="Fighter">Fighter</option>
                                    <option value="Mage">Mage</option>
									<option value="Marksman">Marksman</option>
                                    <option value="Support">Support</option>
									<option value="Tank">Tank</option>
								</select>
							</fieldset>
				<!-- </div> -->
				</div>	
			</div>
		</div>
		<div v-for="(champ, index) in champion" :key="index" class="col col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12" v-show="finalname == 'All' || finalname == champ.tags">
			<div class="ui-block">			
				<div class="birthday-item inline-items">
					<div class="author-thumb">
						<img :src=(temp+champ.fullImage) alt="author">
					</div>
					<div class="birthday-author-name">
						<a class="h6 author-name"  @click='goTo(champ.cid)'>{{champ.alias}}</a>
						<div class="birthday-date">{{champ.tags}}</div>
					</div>
				</div>
				
				<!-- ... end Birthday Item -->
			</div>
		</div>
	</div>
</div>

<!-- ... end Your Account Personal Information -->



<a class="back-to-top" href="#">
	<i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i>
</a>


</div>


</template>>

<script>
import headline from '@/components/subgroup/Head.vue'
import loading from '@/components/subgroup/Loading.vue'
setTimeout(function () {
			$('#mypreloader').fadeOut(1000);
		}, 2000)
export default {
  name: 'searchChampion',
  data () {
    return {
        champion: [],
        temp: '/static/img/champion/',
        championname: '',
        finalname: 'All',
    }
  },
  methods: {
      SearchChamp(){
          if(this.championname != null && this.championname != ''){
            this.finalname = this.championname
          }
      },
      init(){
        this.$axios
        .post('/searchChampion-producer/api/showChamp', {
        })
        .then(successResponse => {
		  this.responseResult = JSON.stringify(successResponse.data)
          if (successResponse.data.code === 200) {
            this.champion = successResponse.data.data
          }
          if (successResponse.data.code === 400) {
            alert(successResponse.data.message)
          }
        })
        .catch(failResponse => {})
      },   
      goTo(cid){
            this.$router.push({path:'/championinfo',query:{Cid:cid}});
      },
  },
  mounted() {
      this.init()
  },
  created(){
  },
  components:{
	  headline,
	  loading
  },
}
</script>