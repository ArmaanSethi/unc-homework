program a3
  integer :: rows, cols
  character(len=32) :: arg
  integer i,j,k, flag
  integer :: irow, temp, temp2
  integer, allocatable, dimension(:,:) :: nums
  integer, allocatable, dimension(:) :: temp_row

  call get_command_argument(1, arg)
  ! WRITE(*, *) arg

  open(unit = 1, file=arg)

  read(1, *) rows, cols
  ! print*, rows
  ! print*, cols
  allocate(nums(rows,cols))
  allocate(temp_row(cols))

  read(1,*) ((nums(i,j), j=1,cols), i= 1, rows)
  temp = 0
  
  ! do i = 1, rows
  !   print*, nums(i,:)
  ! end do
  

  !sort numbers in each row in increasing order
  do irow = 1, rows
    temp_row ( : ) = nums(irow, :)
    do i = 1, cols-1
      do j = 1, cols-1
        if (temp_row(j) > temp_row(j+1)) then
          temp = temp_row(j)
          temp2 = temp_row(j+1)
          temp_row(j) = temp2
          temp_row(j+1) = temp
        endif
      enddo
    enddo
    nums(irow, :) = temp_row(:)
  enddo
  
  !sort each row in the matrix
  do i = 1, rows-1
    do j = 1, rows-1
      k = 1
      flag = 0
      !flag determines which column to compare, k is the value
      do while (flag == 0)
        flag = 1
        if( nums(j, k) == nums (j+1, k) ) then
          flag = 0
          k = k+1
        endif
        if (k <= cols) then
          if( nums(j, k) > nums(j+1, k) ) then
            temp_row(:) = nums(j, :)
            nums(j, :) = nums(j+1, :)
            nums(j+1, :) = temp_row(:)
          endif
        endif
      enddo
    enddo
  enddo

  ! do i = 1, rows
  !   print*, nums(i,:)
  ! end do
  
  call get_command_argument(2,arg)
  ! WRITE(*, *) arg

  open(unit=2, file=arg)
  do i = 1, rows
    write(2, *) nums(i,:)
  end do



  deallocate(nums)
  deallocate(temp_row)
  close(1)
  close(2)
END PROGRAM
