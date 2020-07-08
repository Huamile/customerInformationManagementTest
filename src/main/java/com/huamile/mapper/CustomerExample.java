package com.huamile.mapper;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCusidIsNull() {
            addCriterion("cusId is null");
            return (Criteria) this;
        }

        public Criteria andCusidIsNotNull() {
            addCriterion("cusId is not null");
            return (Criteria) this;
        }

        public Criteria andCusidEqualTo(Integer value) {
            addCriterion("cusId =", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotEqualTo(Integer value) {
            addCriterion("cusId <>", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidGreaterThan(Integer value) {
            addCriterion("cusId >", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cusId >=", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidLessThan(Integer value) {
            addCriterion("cusId <", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidLessThanOrEqualTo(Integer value) {
            addCriterion("cusId <=", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidIn(List<Integer> values) {
            addCriterion("cusId in", values, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotIn(List<Integer> values) {
            addCriterion("cusId not in", values, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidBetween(Integer value1, Integer value2) {
            addCriterion("cusId between", value1, value2, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotBetween(Integer value1, Integer value2) {
            addCriterion("cusId not between", value1, value2, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusnameIsNull() {
            addCriterion("cusName is null");
            return (Criteria) this;
        }

        public Criteria andCusnameIsNotNull() {
            addCriterion("cusName is not null");
            return (Criteria) this;
        }

        public Criteria andCusnameEqualTo(String value) {
            addCriterion("cusName =", value, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameNotEqualTo(String value) {
            addCriterion("cusName <>", value, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameGreaterThan(String value) {
            addCriterion("cusName >", value, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameGreaterThanOrEqualTo(String value) {
            addCriterion("cusName >=", value, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameLessThan(String value) {
            addCriterion("cusName <", value, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameLessThanOrEqualTo(String value) {
            addCriterion("cusName <=", value, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameLike(String value) {
            addCriterion("cusName like", "%"+value+"%", "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameNotLike(String value) {
            addCriterion("cusName not like", "%"+value+"%", "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameIn(List<String> values) {
            addCriterion("cusName in", values, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameNotIn(List<String> values) {
            addCriterion("cusName not in", values, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameBetween(String value1, String value2) {
            addCriterion("cusName between", value1, value2, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusnameNotBetween(String value1, String value2) {
            addCriterion("cusName not between", value1, value2, "cusname");
            return (Criteria) this;
        }

        public Criteria andCusaddressIsNull() {
            addCriterion("cusAddress is null");
            return (Criteria) this;
        }

        public Criteria andCusaddressIsNotNull() {
            addCriterion("cusAddress is not null");
            return (Criteria) this;
        }

        public Criteria andCusaddressEqualTo(String value) {
            addCriterion("cusAddress =", value, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressNotEqualTo(String value) {
            addCriterion("cusAddress <>", value, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressGreaterThan(String value) {
            addCriterion("cusAddress >", value, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressGreaterThanOrEqualTo(String value) {
            addCriterion("cusAddress >=", value, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressLessThan(String value) {
            addCriterion("cusAddress <", value, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressLessThanOrEqualTo(String value) {
            addCriterion("cusAddress <=", value, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressLike(String value) {
            addCriterion("cusAddress like", "%"+value+"%", "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressNotLike(String value) {
            addCriterion("cusAddress not like", "%"+value+"%", "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressIn(List<String> values) {
            addCriterion("cusAddress in", values, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressNotIn(List<String> values) {
            addCriterion("cusAddress not in", values, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressBetween(String value1, String value2) {
            addCriterion("cusAddress between", value1, value2, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andCusaddressNotBetween(String value1, String value2) {
            addCriterion("cusAddress not between", value1, value2, "cusaddress");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", "%"+value+"%", "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", "%"+value+"%", "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andCustelIsNull() {
            addCriterion("cusTel is null");
            return (Criteria) this;
        }

        public Criteria andCustelIsNotNull() {
            addCriterion("cusTel is not null");
            return (Criteria) this;
        }

        public Criteria andCustelEqualTo(String value) {
            addCriterion("cusTel =", value, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelNotEqualTo(String value) {
            addCriterion("cusTel <>", value, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelGreaterThan(String value) {
            addCriterion("cusTel >", value, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelGreaterThanOrEqualTo(String value) {
            addCriterion("cusTel >=", value, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelLessThan(String value) {
            addCriterion("cusTel <", value, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelLessThanOrEqualTo(String value) {
            addCriterion("cusTel <=", value, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelLike(String value) {
            addCriterion("cusTel like", "%"+value+"%", "custel");
            return (Criteria) this;
        }

        public Criteria andCustelNotLike(String value) {
            addCriterion("cusTel not like", "%"+value+"%", "custel");
            return (Criteria) this;
        }

        public Criteria andCustelIn(List<String> values) {
            addCriterion("cusTel in", values, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelNotIn(List<String> values) {
            addCriterion("cusTel not in", values, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelBetween(String value1, String value2) {
            addCriterion("cusTel between", value1, value2, "custel");
            return (Criteria) this;
        }

        public Criteria andCustelNotBetween(String value1, String value2) {
            addCriterion("cusTel not between", value1, value2, "custel");
            return (Criteria) this;
        }

        public Criteria andCusemailIsNull() {
            addCriterion("cusEmail is null");
            return (Criteria) this;
        }

        public Criteria andCusemailIsNotNull() {
            addCriterion("cusEmail is not null");
            return (Criteria) this;
        }

        public Criteria andCusemailEqualTo(String value) {
            addCriterion("cusEmail =", value, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailNotEqualTo(String value) {
            addCriterion("cusEmail <>", value, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailGreaterThan(String value) {
            addCriterion("cusEmail >", value, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailGreaterThanOrEqualTo(String value) {
            addCriterion("cusEmail >=", value, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailLessThan(String value) {
            addCriterion("cusEmail <", value, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailLessThanOrEqualTo(String value) {
            addCriterion("cusEmail <=", value, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailLike(String value) {
            addCriterion("cusEmail like", "%"+value+"%", "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailNotLike(String value) {
            addCriterion("cusEmail not like", "%"+value+"%", "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailIn(List<String> values) {
            addCriterion("cusEmail in", values, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailNotIn(List<String> values) {
            addCriterion("cusEmail not in", values, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailBetween(String value1, String value2) {
            addCriterion("cusEmail between", value1, value2, "cusemail");
            return (Criteria) this;
        }

        public Criteria andCusemailNotBetween(String value1, String value2) {
            addCriterion("cusEmail not between", value1, value2, "cusemail");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}