import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/yxStoreCouponIssue',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/yxStoreCouponIssue/' + id,
    method: 'delete'
  })
}

export function addcoupon(data) {
  return request({
    url: 'api/yxStoreCouponIssue/sendcoupon',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: 'api/yxStoreCouponIssue',
    method: 'put',
    data
  })
}
