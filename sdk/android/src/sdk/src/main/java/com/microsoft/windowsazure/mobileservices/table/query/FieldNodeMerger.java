/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */

/**
 * FieldNodeMerger.java
 */
package com.microsoft.windowsazure.mobileservices.table.query;

/**
 * Class that represents a field node merger
 */
class FieldNodeMerger implements QueryNodeVisitor<QueryNode> {
    private FieldNode mLeftNode;

    /**
     * Constructor for FieldNodeMerger
     *
     * @param leftNode The left field node
     */
    FieldNodeMerger(FieldNode leftNode) {
        this.mLeftNode = leftNode;
    }

    @Override
    public QueryNode visit(ConstantNode rightNode) {
        throw QueryNodeMerger.getInvalidSequenceException();
    }

    @Override
    public QueryNode visit(FieldNode rightNode) {
        throw QueryNodeMerger.getInvalidSequenceException();
    }

    @Override
    public QueryNode visit(UnaryOperatorNode rightNode) {
        throw QueryNodeMerger.getInvalidSequenceException();
    }

    @Override
    public QueryNode visit(BinaryOperatorNode rightNode) {
        if (rightNode.getLeftArgument() != null) {
            throw QueryNodeMerger.getInvalidSequenceException();
        }

        rightNode.setLeftArgument(this.mLeftNode);

        return rightNode;
    }

    @Override
    public QueryNode visit(FunctionCallNode rightNode) {
        throw QueryNodeMerger.getInvalidSequenceException();
    }
}