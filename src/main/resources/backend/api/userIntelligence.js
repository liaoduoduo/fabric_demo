// 查询列表接口
const getIntelligencePage = (params) => {
  return $axios({
    url: '/userIntelligence/page',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// （批量）添加情报至协同任务
const addCoTask = (params) => {
  return $axios({
    url: '/cotasking/save',
    method: 'post',
    params: params
  })
}

const queryFileHash = (row) => {
  return $axios({
    url: `/userIntelligence/hash`,
    method: 'post',
    data :  row
  })
}


