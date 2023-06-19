# Data Cache Simulation 2: 4 Variables

.globl main

.data
yourname1:	.asciiz 	"xyz1xyz1"
.align 64
yourname2:	.asciiz 	"xyz2xyz2"
.align 64
yourname3:	.asciiz 	"xyz3xyz3"
.align 64
yourname4:	.asciiz 	"xyz4xyz4"

.text
main:
    li $t2, 0				# Initialize loop counter for entire program

outerLoop:
    beq $t2, 2, Exit		# If we have run the whole program twice, go to Exit

    la $s0, yourname1		# Load address of yourname1 into $s0
    la $s1, yourname2		# Load address of yourname2 into $s1
    la $s2, yourname3		# Load address of yourname3 into $s2
    la $s3, yourname4		# Load address of yourname4 into $s3

    li $t0, 0				# Initialize loop counter

Loop:
    beq $t0, 4, innerLoopExit	# If we have loaded 4 words, go to innerLoopExit

    lw $t1, 0($s0			# Load word from yourname1
    addiu $s0, $s0, 4		# Move to the next word
    lw $t1, 0($s1			# Load word from yourname2
    addiu $s1, $s1, 4		# Move to the next word
    lw $t1, 0($s2			# Load word from yourname3
    addiu $s2, $s2, 4		# Move to the next word
    lw $t1, 0($s3			# Load word from yourname4
    addiu $s3, $s3, 4		# Move to the next word

    addiu $t0, $t0, 1		# Increment loop counter
    j Loop					# Jump to the start of Loop

innerLoopExit:
    addiu $t2, $t2, 1		# Increment outer loop counter
    j outerLoop				# Jump to the start of outer loop

Exit:




# ==========================={ Notes }=========================== #
# No syscalls to not skew the results from using data caches.
# Use LRU rather than random replacement so results are consistant.

# ==========================={ Notes }=========================== #
# Question 1
#
# 1.With a block size of one (1) and Direct Mapped, run the simulator.
#   What is the hit rate? 0%
#
# 2. Still running Direct Mapped, what is the hit rate with a block
#    size of two (2)? 50%
#
# 3. Still running Direct Mapped, what is the hit rate with a block
#    size of four (4)? 75%
#
# 4. Now configure the cache as 2-way set associative with a block
#    size of one and iterate twice through the data loading sequence
#    (put a loop around the original sequence). What is the hit rate? 67%
#
# 5. Next configure the cache as 4-way set associative with a block
#    size of one and still iterating twice through the data loading
#    sequence, what is the hit rate? 0%
#
# 6. For the same as immediately above with a block size of 4, what
#    will be the hit rate? 75%
#
# 7. For the same as immediately above, but with a load sequence
#    iteration count raised to 4? 94%
#
#
# Question 2
# 1. What is the best hit rate that can be achieved in a single
#    iteration through your arrays? 0%
#
# 2. What is the hit rate for two iterations? 50%
#
# 3. What is the hit rate for 4-way set associative, block size
#    of 1 and 2 iterations? 50%