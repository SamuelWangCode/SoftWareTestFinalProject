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
					<h1>Search The Guide</h1>
					<p>Here you can search all guides you want!!</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ... end Main Header Account -->
<div class="container">
	<div class="row">
		<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="ui-block responsive-flex">
				<div class="ui-block-title">
					<div class="h6 title">Search Guides</div>
					<div class="w-search">
						<div class="form-group with-button">
							<input class="form-control" type="text" v-model="guidename" placeholder="Search the forums...">
							<button @click="SearchGuide">
								<i class="fa fa-search-plus fa-2x" aria-hidden="true"></i>
							</button>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</div>



<div class="container">
	<div class="row">
		<div class="col col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">

			<div class="ui-block">

				
				<!-- Forums Table -->
				
				<table class="forums-table">
				
					<thead>
				
					<tr>
				
						<th class="forum">
							Title
						</th>
						<th class="topics">
						</th>
				
						<th class="posts">
						</th>
				
						<th class="freshness">
							Likes
						</th>
				
					</tr>
				
					</thead>
				
					<tbody>
					<tr v-for="(guide, index) in guides" :key="index">
						<td class="forum">
							<div class="forum-item">
								<img :src=(temp+guide.championImg) alt="forum">
								<div class="content">
									<a @click="goTo(guide.gid)" class="h6 title">{{guide.title}}</a>
								</div>
							</div>
						</td>
						<td class="topics"></td>
						<td class="posts">
						</td>
						<td class="freshness">
							<div class="author-freshness">
								<a class="h6 title">{{guide.likeNum}}</a>
							</div>
						</td>
					</tr>				
					</tbody>
				</table>
                <div v-show="!guides.length" class="text" style="text-align:center">
                    No Guides
                </div>					
				<!-- ... end Forums Table -->
			</div>

		</div>

		<div class="col col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
			<div class="ui-block">
				<div class="ui-block-title">
					<h6 class="title">Guide You May Like</h6>
				</div>
				<div class="ui-block-content">

					
					<!-- Widget Featured Topics -->
					
					<ul class="widget w-featured-topics">
						<li v-for="(guide, index) in favguides" :key="index">
							<i class="icon fa fa-star" aria-hidden="true"></i>
							<div class="content">
								<a @click="goTo(guide.gid)" class="h6 title">{{guide.title}}</a>
							</div>
						</li>
					</ul>
					
					<!-- ... end Widget Featured Topics -->
                <div v-show="!favguides.length" class="text" style="text-align:center">
                    You haven't chose your prefered guide
                </div>   				
                </div>
             
			</div>

			<div class="ui-block">
				<div class="ui-block-title">
					<h6 class="title">Hot Guides</h6>
				</div>
				<div class="ui-block-content">

					
					<!-- Widget Recent Topics -->
					
					<ul class="widget w-featured-topics">
						<li v-for="(guide, index) in hotguides" :key="index">
							<div class="content">
								<a @click="goTo(guide.gid)" class="h6 title">{{guide.title}}</a>
							</div>
						</li>
					</ul>
					
					<!-- ... end Widget Recent Topics -->
                <div v-show="!hotguides.length" class="text" style="text-align:center">
                        No Guides
                </div>  				
                </div>
			</div>
		</div>
	</div>
</div>



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
  name: 'searchGuide',
  data () {
    return {
         guides: [],
         favguides: [],
         hotguides: [],
         temp: '/static/img/champion/',
         guidename: '',
         uid : sessionStorage.getItem("UuID")
    }
  },
  methods: {
      SearchGuide(){
        if(this.guidename != ''){
		this.$axios
		.get('/guide-producer/api/searchGuideByTitle/' + this.guidename)
        .then(successResponse => {
			this.responseResult = JSON.stringify(successResponse.data)
        	if (successResponse.data.code === 200) {
                this.guides = successResponse.data.data

                if(successResponse.data.data == ''){
                    alert("No such guide")
                }				
        	}
        	if (successResponse.data.code === 400) {
				alert(successResponse.data.message)
			}
        })
        .catch(failResponse => {})
        }
      },
      init(){
          if(this.uid != null){
            this.$axios 
		    .get('/guide-producer/api/getPreferGuide/' + this.uid)
            .then(successResponse => {
			    this.responseResult = JSON.stringify(successResponse.data)
        	    if (successResponse.data.code === 200) {
                    this.favguides = successResponse.data.data			
        	}
        	if (successResponse.data.code === 400) {
				alert(successResponse.data.message)
			}
            })
            .catch(failResponse => {})                          
          }
        this.$axios
        .post('/guide-producer/api/searchGuideByLikeNum', {})
        .then(successResponse => {
		  this.responseResult = JSON.stringify(successResponse.data)
          if (successResponse.data.code === 200) {
              this.hotguides = successResponse.data.data
          }
          if (successResponse.data.code === 400) {
            alert(successResponse.data.message)
          }
        })
        .catch(failResponse => {}) 
      },   
      goTo(gid){
            this.$router.push({path:'/guideinfo',query:{Gid:gid}});
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