package com.samwellstore.librarymanagement.utils.mapper;


    public  interface Mapper<A,B>{
        B mapTo(A a);
        A mapFrom(B b);
    }

