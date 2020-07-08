package com.huamile.mapper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository()
public class EmployeesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeesExample() {
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

        public Criteria andEmpidIsNull() {
            addCriterion("empId is null");
            return (Criteria) this;
        }

        public Criteria andEmpidIsNotNull() {
            addCriterion("empId is not null");
            return (Criteria) this;
        }

        public Criteria andEmpidEqualTo(Integer value) {
            addCriterion("empId =", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotEqualTo(Integer value) {
            addCriterion("empId <>", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidGreaterThan(Integer value) {
            addCriterion("empId >", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidGreaterThanOrEqualTo(Integer value) {
            addCriterion("empId >=", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidLessThan(Integer value) {
            addCriterion("empId <", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidLessThanOrEqualTo(Integer value) {
            addCriterion("empId <=", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidIn(List<Integer> values) {
            addCriterion("empId in", values, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotIn(List<Integer> values) {
            addCriterion("empId not in", values, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidBetween(Integer value1, Integer value2) {
            addCriterion("empId between", value1, value2, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotBetween(Integer value1, Integer value2) {
            addCriterion("empId not between", value1, value2, "empid");
            return (Criteria) this;
        }

        public Criteria andEmploginnameIsNull() {
            addCriterion("empLoginName is null");
            return (Criteria) this;
        }

        public Criteria andEmploginnameIsNotNull() {
            addCriterion("empLoginName is not null");
            return (Criteria) this;
        }

        public Criteria andEmploginnameEqualTo(String value) {
            addCriterion("empLoginName =", value, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameNotEqualTo(String value) {
            addCriterion("empLoginName <>", value, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameGreaterThan(String value) {
            addCriterion("empLoginName >", value, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameGreaterThanOrEqualTo(String value) {
            addCriterion("empLoginName >=", value, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameLessThan(String value) {
            addCriterion("empLoginName <", value, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameLessThanOrEqualTo(String value) {
            addCriterion("empLoginName <=", value, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameLike(String value) {
            addCriterion("empLoginName like", "%"+value+"%", "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameNotLike(String value) {
            addCriterion("empLoginName not like", "%"+value+"%", "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameIn(List<String> values) {
            addCriterion("empLoginName in", values, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameNotIn(List<String> values) {
            addCriterion("empLoginName not in", values, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameBetween(String value1, String value2) {
            addCriterion("empLoginName between", value1, value2, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmploginnameNotBetween(String value1, String value2) {
            addCriterion("empLoginName not between", value1, value2, "emploginname");
            return (Criteria) this;
        }

        public Criteria andEmppasswordIsNull() {
            addCriterion("empPassword is null");
            return (Criteria) this;
        }

        public Criteria andEmppasswordIsNotNull() {
            addCriterion("empPassword is not null");
            return (Criteria) this;
        }

        public Criteria andEmppasswordEqualTo(String value) {
            addCriterion("empPassword =", value, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordNotEqualTo(String value) {
            addCriterion("empPassword <>", value, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordGreaterThan(String value) {
            addCriterion("empPassword >", value, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordGreaterThanOrEqualTo(String value) {
            addCriterion("empPassword >=", value, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordLessThan(String value) {
            addCriterion("empPassword <", value, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordLessThanOrEqualTo(String value) {
            addCriterion("empPassword <=", value, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordLike(String value) {
            addCriterion("empPassword like", "%"+value+"%", "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordNotLike(String value) {
            addCriterion("empPassword not like", "%"+value+"%", "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordIn(List<String> values) {
            addCriterion("empPassword in", values, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordNotIn(List<String> values) {
            addCriterion("empPassword not in", values, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordBetween(String value1, String value2) {
            addCriterion("empPassword between", value1, value2, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmppasswordNotBetween(String value1, String value2) {
            addCriterion("empPassword not between", value1, value2, "emppassword");
            return (Criteria) this;
        }

        public Criteria andEmptelIsNull() {
            addCriterion("empTel is null");
            return (Criteria) this;
        }

        public Criteria andEmptelIsNotNull() {
            addCriterion("empTel is not null");
            return (Criteria) this;
        }

        public Criteria andEmptelEqualTo(String value) {
            addCriterion("empTel =", value, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelNotEqualTo(String value) {
            addCriterion("empTel <>", value, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelGreaterThan(String value) {
            addCriterion("empTel >", value, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelGreaterThanOrEqualTo(String value) {
            addCriterion("empTel >=", value, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelLessThan(String value) {
            addCriterion("empTel <", value, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelLessThanOrEqualTo(String value) {
            addCriterion("empTel <=", value, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelLike(String value) {
            addCriterion("empTel like", "%"+value+"%", "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelNotLike(String value) {
            addCriterion("empTel not like", "%"+value+"%", "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelIn(List<String> values) {
            addCriterion("empTel in", values, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelNotIn(List<String> values) {
            addCriterion("empTel not in", values, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelBetween(String value1, String value2) {
            addCriterion("empTel between", value1, value2, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmptelNotBetween(String value1, String value2) {
            addCriterion("empTel not between", value1, value2, "emptel");
            return (Criteria) this;
        }

        public Criteria andEmpnameIsNull() {
            addCriterion("empName is null");
            return (Criteria) this;
        }

        public Criteria andEmpnameIsNotNull() {
            addCriterion("empName is not null");
            return (Criteria) this;
        }

        public Criteria andEmpnameEqualTo(String value) {
            addCriterion("empName =", value, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameNotEqualTo(String value) {
            addCriterion("empName <>", value, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameGreaterThan(String value) {
            addCriterion("empName >", value, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameGreaterThanOrEqualTo(String value) {
            addCriterion("empName >=", value, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameLessThan(String value) {
            addCriterion("empName <", value, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameLessThanOrEqualTo(String value) {
            addCriterion("empName <=", value, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameLike(String value) {
            addCriterion("empName like", "%"+value+"%", "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameNotLike(String value) {
            addCriterion("empName not like", "%"+value+"%", "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameIn(List<String> values) {
            addCriterion("empName in", values, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameNotIn(List<String> values) {
            addCriterion("empName not in", values, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameBetween(String value1, String value2) {
            addCriterion("empName between", value1, value2, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpnameNotBetween(String value1, String value2) {
            addCriterion("empName not between", value1, value2, "empname");
            return (Criteria) this;
        }

        public Criteria andEmpemailIsNull() {
            addCriterion("empEmail is null");
            return (Criteria) this;
        }

        public Criteria andEmpemailIsNotNull() {
            addCriterion("empEmail is not null");
            return (Criteria) this;
        }

        public Criteria andEmpemailEqualTo(String value) {
            addCriterion("empEmail =", value, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailNotEqualTo(String value) {
            addCriterion("empEmail <>", value, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailGreaterThan(String value) {
            addCriterion("empEmail >", value, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailGreaterThanOrEqualTo(String value) {
            addCriterion("empEmail >=", value, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailLessThan(String value) {
            addCriterion("empEmail <", value, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailLessThanOrEqualTo(String value) {
            addCriterion("empEmail <=", value, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailLike(String value) {
            addCriterion("empEmail like", "%"+value+"%", "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailNotLike(String value) {
            addCriterion("empEmail not like", "%"+value+"%", "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailIn(List<String> values) {
            addCriterion("empEmail in", values, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailNotIn(List<String> values) {
            addCriterion("empEmail not in", values, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailBetween(String value1, String value2) {
            addCriterion("empEmail between", value1, value2, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmpemailNotBetween(String value1, String value2) {
            addCriterion("empEmail not between", value1, value2, "empemail");
            return (Criteria) this;
        }

        public Criteria andEmptypeIsNull() {
            addCriterion("empType is null");
            return (Criteria) this;
        }

        public Criteria andEmptypeIsNotNull() {
            addCriterion("empType is not null");
            return (Criteria) this;
        }

        public Criteria andEmptypeEqualTo(String value) {
            addCriterion("empType =", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotEqualTo(String value) {
            addCriterion("empType <>", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeGreaterThan(String value) {
            addCriterion("empType >", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeGreaterThanOrEqualTo(String value) {
            addCriterion("empType >=", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeLessThan(String value) {
            addCriterion("empType <", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeLessThanOrEqualTo(String value) {
            addCriterion("empType <=", value, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeLike(String value) {
            addCriterion("empType like", "%"+value+"%", "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotLike(String value) {
            addCriterion("empType not like", "%"+value+"%", "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeIn(List<String> values) {
            addCriterion("empType in", values, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotIn(List<String> values) {
            addCriterion("empType not in", values, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeBetween(String value1, String value2) {
            addCriterion("empType between", value1, value2, "emptype");
            return (Criteria) this;
        }

        public Criteria andEmptypeNotBetween(String value1, String value2) {
            addCriterion("empType not between", value1, value2, "emptype");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("roleId is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("roleId is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Integer value) {
            addCriterion("roleId =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Integer value) {
            addCriterion("roleId <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Integer value) {
            addCriterion("roleId >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("roleId >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Integer value) {
            addCriterion("roleId <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Integer value) {
            addCriterion("roleId <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Integer> values) {
            addCriterion("roleId in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Integer> values) {
            addCriterion("roleId not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Integer value1, Integer value2) {
            addCriterion("roleId between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Integer value1, Integer value2) {
            addCriterion("roleId not between", value1, value2, "roleid");
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