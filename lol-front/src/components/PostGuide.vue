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
					<h1>Your Account Dashboard</h1>
					<p>Welcome to your account dashboard! Here you’ll find everything you need to change your profile!!</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ... end Main Header Account -->


<!-- Your Account Personal Information -->

<div class="container">
	<div class="row">
		<div class="col col-xl-9 order-xl-2 col-lg-9 order-lg-2 col-md-12 order-md-1 col-sm-12 col-12">
			<div class="ui-block">
				<div class="ui-block-title">
					<h6 class="title">Post Guide</h6>
                    <h6 class="title" style="float:right">step{{step}}</h6>
				</div>
				<div class="ui-block-content">

					
					<!-- Personal Information Form  -->
					<div class="row" v-show="step==1">

						<div class="col col-lg-12 col-md-12 col-sm-12 col-15">
							<div class="form-group label-floating">
								<input class="form-control"  type="text" v-model="guides.title" placeholder="enter your guide's title..">
							</div>
					
						</div>
					
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Next</button>
						</div>
					
					</div>


					<div class="row" v-show="step==2">
                            
                        <div class="col col-lg-3 col-md-12 col-sm-12 col-15">					
						</div>
						<div class="col col-lg-6 col-md-12 col-sm-12 col-15">
							<div class="form-group label-floating is-select">
								<label class="control-label">Select Your Guide's Category</label>
								<select v-model="guides.championTag">
									<option value="1">Attack</option>
									<option value="2">Defend</option>
									<option value="3">Support</option>
								</select>
							</div>
					
						</div>
					
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Next</button>
						</div>
					
					</div>

					<div class="row" v-show="step==3">
                            
                        <div class="col col-lg-12 col-md-12 col-sm-12 col-15">
                            <label class="control-label">Select Your Champion: {{guides.championName}}</label>
						</div>
					
						<div v-for="(champ, index) in champion" :key="index" class="col col-lg-3 col-md-12 col-sm-12 col-15">
							<div class="birthday-item inline-items">
					            <div class="author-thumb">
						            <img :src=(temp+champ.fullImage) alt="author">
					            </div>
					            <div class="birthday-author-name">
						            <a class="h6 author-name" @click="chooseChamp(champ)">{{champ.alias}}</a>
					            </div>
				            </div>				
						</div>
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Next</button>
						</div>

					</div>
					
 					<div class="row" v-show="step==4">
                            
                        <div class="col col-lg-12 col-md-12 col-sm-12 col-15">
                            <label class="control-label">Select Early Game Item(choose two items): {{sitem1.itemName}} / {{sitem2.itemName}} </label>
						</div>
					
						<div v-for="(item, index) in items" :key="index" class="col col-lg-1 col-md-12 col-sm-12 col-15">
							<div class="birthday-item inline-items">
					            <div class="author-thumb">
						            <a @click="chooseItem(item)"><img :src=(itemp+item.itemImg) alt="author"></a>
					            </div>
				            </div>				
						</div>
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Next</button>
						</div>					
					</div>

 					<div class="row" v-show="step==5">
                            
                        <div class="col col-lg-12 col-md-12 col-sm-12 col-15">
                            <label class="control-label">Select Middle Game Item(choose two items): {{sitem1.itemName}} / {{sitem2.itemName}} </label>
						</div>
					
						<div v-for="(item, index) in items" :key="index" class="col col-lg-1 col-md-12 col-sm-12 col-15">
							<div class="birthday-item inline-items">
					            <div class="author-thumb">
						            <a @click="chooseItem(item)"><img :src=(itemp+item.itemImg) alt="author"></a>
					            </div>
				            </div>				
						</div>
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Next</button>
						</div>					
					</div>

 					<div class="row" v-show="step==6">
                            
                        <div class="col col-lg-12 col-md-12 col-sm-12 col-15">
                            <label class="control-label">Select Late Game Item(choose two items): {{sitem1.itemName}} / {{sitem2.itemName}} </label>
						</div>
					
						<div v-for="(item, index) in items" :key="index" class="col col-lg-1 col-md-12 col-sm-12 col-15">
							<div class="birthday-item inline-items">
					            <div class="author-thumb">
						            <a @click="chooseItem(item)"><img :src=(itemp+item.itemImg) alt="author"></a>
					            </div>
				            </div>				
						</div>
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Next</button>
						</div>					
					</div>    
                    
 					<div class="row" v-show="step==7">
                            
                        <div class="col col-lg-12 col-md-12 col-sm-12 col-15">
                            <label class="control-label">Input Your Description:</label>
						</div>
                         <div class="col col-lg-12 col-md-12 col-sm-12 col-15">
                            <textarea v-model="guides.context" rows="7" cols="135" placeholder="enter your description.."></textarea>
						</div>
						<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
							<button class="btn btn-primary btn-lg" @click="nextstep">Finish</button>
						</div>					
					</div>                                       
					<!-- ... end Personal Information Form  -->
				</div>
			</div>
		</div>



		<div class="col col-xl-3 order-xl-1 col-lg-3 order-lg-1 col-md-12 order-md-2 col-sm-12 col-12 responsive-display-none">
			<div class="ui-block">

				<!-- Your Profile  -->
				
				<div class="your-profile">
					<div class="ui-block-title ui-block-title-small">
						<h6 class="title">Your PROFILE</h6>
					</div>
				
					<div id="accordion" role="tablist" aria-multiselectable="true">
						<div class="card">
							<div class="card-header" role="tab" id="headingOne">
								<h6 class="mb-0">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
										Profile Settings
										<svg class="olymp-dropdown-arrow-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-dropdown-arrow-icon"></use></svg>
									</a>
								</h6>
							</div>
				
							<div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
								<ul class="your-profile-menu">
									<li>
										<a href="http://localhost:8080/personalinfo">Personal Information</a>
									</li>
									<li>
										<a href="http://localhost:8080/myfollows">My Follows</a>
									</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="ui-block-title">
						<a href="http://localhost:8080/searchuser" class="h6 title">Search User</a>
					</div>

					<div class="ui-block-title ui-block-title-small">
						<h6 class="title">FAVOURITE Guide</h6>
					</div>
					<div class="ui-block-title">
						<a href="http://localhost:8080/postguide" class="h6 title">Create Guide</a>
					</div>
					<div class="ui-block-title">
						<a href="http://localhost:8080/myguide" class="h6 title">My Guide</a>
					</div>
				</div>
				
				<!-- ... end Your Profile  -->

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
			$('#mypreloader').fadeOut(600);
		}, 1500)
export default {
  name: 'postGuide',
  data () {
    return {
         guides:{
             title: '',
             context: '',
             championTag: '1',
             item1: '',
             item2: '',
             item3: '',
             item4: '',
             item5: '',
             item6: '',
             championImg: '',
             championName: ''
         },
        champion: [],
        items:[],         
        temp: '/static/img/champion/',
        itemp: '/static/img/item/',
		back: '.png',
        step: 1,
        uid : sessionStorage.getItem("UuID"),
        itemnum: '1',
        sitem1: [],
        sitem2: [],
		iflag: '0',
    }
  },
  methods: {
	  getItem(){
		this.$axios
        .post('/guide-producer/api/getItem', {
        })
        .then(successResponse => {
		  this.responseResult = JSON.stringify(successResponse.data)
          if (successResponse.data.code === 200) {
              this.items = successResponse.data.data
          }
          if (successResponse.data.code === 400) {
            alert(successResponse.data.message)
          }
        })
        .catch(failResponse => {})
	  },
	  nextstep () {
          if(this.step == 2){
              this.getChampInfo()
              this.step++
          }else if(this.step == 3){
              this.getItem()
              this.step++
          }else if(this.step == 7){
			  this.postguide()	  		  				  				  			  			  
          }else if(this.step < 6 && this.step > 3){
              if(this.iflag == 1){
                    this.step++
                    this.itemnum = 1
                    this.iflag = 0                     
                    this.sitem1 = []
                    this.sitem2 = []
              }else{
                  alert("请选择两个装备")
              }
          }else{
              this.step++
          }
     },
      getChampInfo(){
		this.$axios
        .get('/searchChampion-producer/api/searchChampBytag/' + this.guides.championTag)
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.champion = successResponse.data.data
            this.$dlg.toast("success", {messageType: 'success', closeTime: 5})
          }
          if (successResponse.data.code === 400) {
            this.$dlg.toast(successResponse.data.msg, {messageType: 'error', closeTime: 5})
          }
        })
        .catch(failResponse => {})
      },
      chooseChamp(champ){
          this.guides.championName = champ.alias
          this.guides.championImg = champ.fullImage
      },
      chooseItem(item){
        if(this.step == 4){
            if(this.itemnum == 1){
                this.sitem1 = item
                this.guides.item1 = item.iid
                this.itemnum++
            }else{
                this.sitem2 = item
                this.guides.item2 = item.iid
                this.itemnum--
                this.iflag = 1                
            }
        }else if(this.step == 5){
            if(this.itemnum == 1){
                this.sitem1 = item
                this.guides.item3 = item.iid
                this.itemnum++
            }else{
                this.sitem2 = item
                this.guides.item4 = item.iid
                this.itemnum--    
                this.iflag = 1                             
            }
        }else{
            if(this.itemnum == 1){
                this.sitem1 = item
                this.guides.item5 = item.iid
                this.itemnum++
            }else{
                this.sitem2 = item
                this.guides.item6 = item.iid
                this.itemnum--   
                this.iflag = 1                              
            }            
        }
	  },
	  postguide(){
		  this.$axios
          .post('/guide-producer/api/postGuide', {
			  title: this.guides.title,
			  championTag:this.guides.championTag,
			  championName:this.guides.championName,
			  championImg:this.guides.championImg,
			  item1: this.guides.item1,
			  item2: this.guides.item2, 
			  item3:this.guides.item3,
			  item4:this.guides.item4,
			  item5:this.guides.item5,
			  item6:this.guides.item6,		
			  context:this.guides.context,
			  uid: this.uid
          })
          .then(successResponse => {
            this.responseResult = JSON.stringify(successResponse.data)
            if (successResponse.data.code === 200) {
			  alert(successResponse.data.data)
			  location.reload()
            }
            if (successResponse.data.code === 400) {
              alert(successResponse.data.message)
            }
          })
          .catch(failResponse => {})
	  }
  },
  mounted() {
  },
  created(){
  },
  components:{
	  headline,
	  loading
  },
}
</script>

